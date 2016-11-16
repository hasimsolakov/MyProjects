package presenter.provider.type;

import java.lang.annotation.Annotation;

/**
 * Created by Hashim on 20.8.2016 г..
 */
public interface TypeProvider {

    Class[] getClassesByAnnotation(Class<? extends Annotation> annotation);
}
