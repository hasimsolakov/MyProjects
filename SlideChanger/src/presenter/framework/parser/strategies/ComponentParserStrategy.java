package presenter.framework.parser.strategies;


import presenter.framework.lifecycle.dependency.Component;
import presenter.framework.lifecycle.dependency.Primary;
import presenter.provider.type.TypeProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ComponentParserStrategy implements AnnotationParserStrategy<Class, Class> {

    private TypeProvider provider;

    public ComponentParserStrategy(TypeProvider provider) {
        this.provider = provider;
    }

    @Override
    public void parse(Map<Class, Class> cachedResult) {
        Map<Class, List<Class>> componentsBySuperClasses = new HashMap<>();
        Class [] classesByAnnotation = this.provider.getClassesByAnnotation(Component.class);
        for (Class annotatedClass : classesByAnnotation) {
            for (Class parent : annotatedClass.getInterfaces()) {
                if (!componentsBySuperClasses.containsKey(parent)) {
                    List<Class> subClasses = new ArrayList<>();
                    subClasses.add(annotatedClass);
                    componentsBySuperClasses.put(parent,subClasses);
                }
                else{
                    componentsBySuperClasses.get(parent).add(annotatedClass);
                }
            }
        }

        for (Map.Entry<Class, List<Class>> components: componentsBySuperClasses.entrySet()) {
            if (components.getValue().size() == 1){
                cachedResult.put(components.getKey(), components.getValue().get(0));
            }
            else {
                List<Class> subClasses = components.getValue();
                Stream<Class> primarySubClasses = subClasses.stream().filter(clazz -> clazz.isAnnotationPresent(Primary.class));
                long primarySubClassesCount = primarySubClasses.count();
                if (primarySubClassesCount > 1){
                    throw new UnsupportedOperationException("Cannot map dependency "
                            + components.getKey()
                            + " there are more than one candidates for injection annotated with Primary");
                }
                else if (primarySubClassesCount < 1){
                    throw new UnsupportedOperationException("Cannot map dependency "
                            + components.getKey()
                            + " there are more than one candidates for injection and none of them is annotated with Primary");
                }

                Class primaryClass = primarySubClasses.toArray(Class[]::new)[0];
                cachedResult.put(components.getKey(), primaryClass);
            }
        }
    }
}
