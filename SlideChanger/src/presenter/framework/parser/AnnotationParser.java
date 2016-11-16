package presenter.framework.parser;


import presenter.framework.parser.strategies.AnnotationParserStrategy;

import java.util.Map;

/**
 * Created by Hashim on 21.8.2016 г..
 */
public interface AnnotationParser {
    <K, V> void parse(AnnotationParserStrategy<K, V> strategy, Map<K, V> result) throws InstantiationException, IllegalAccessException;
}
