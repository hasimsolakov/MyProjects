package presenter.framework.parser.strategies;

import java.util.Map;

/**
 * Created by Hashim on 21.8.2016 г..
 */
public interface AnnotationParserStrategy<K, V> {
    void parse(Map<K, V> result) throws IllegalAccessException, InstantiationException;
}
