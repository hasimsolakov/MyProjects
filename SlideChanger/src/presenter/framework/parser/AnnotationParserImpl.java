package presenter.framework.parser;


import presenter.framework.parser.strategies.AnnotationParserStrategy;

import java.util.Map;

public class AnnotationParserImpl implements AnnotationParser {
    @Override
    public <K, V> void parse(AnnotationParserStrategy<K, V> strategy, Map<K, V> result) throws InstantiationException, IllegalAccessException {
        strategy.parse(result);
    }
}
