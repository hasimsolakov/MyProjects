package presenter.provider.type;

import presenter.framework.lifecycle.dependency.Component;
import presenter.framework.lifecycle.dependency.ScanPackage;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;

@ScanPackage(packagesToScan = "presenter")
@Component
public class TypeProviderImpl implements TypeProvider{

    private static final String[] packageNames = TypeProviderImpl.class.getAnnotation(ScanPackage.class).packagesToScan();
    private Class[] classes;

    private Map<Class, Class[]> classesByAnnotation;

    public TypeProviderImpl() throws IOException {
        this.classesByAnnotation = new HashMap<>();
        this.classes = getClassesOfPackagesToScan();
    }

    @Override
    public Class[] getClassesByAnnotation(Class<? extends Annotation> annotation) {
        if (this.classesByAnnotation.containsKey(annotation)) {
            return this.classesByAnnotation.get(annotation);
        }

        Class[] result = Arrays.stream(this.classes)
                .filter(c -> c.isAnnotationPresent(annotation))
                .toArray(Class[]::new);

        this.classesByAnnotation.put(annotation, result);

        return result;
    }

    private static Class[] getClassesOfPackagesToScan(){
        List<Class> classes = new ArrayList<>();
        for (String packageName : packageNames) {
            try {
                classes.addAll(Arrays.asList(getClasses(packageName)));
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }

        Class[] classesArray = new Class[classes.size()];
        return classes.toArray(classesArray);
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static Class<?>[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                classes.add(Class.forName(className));
            }
        }

        return classes;
    }
}
