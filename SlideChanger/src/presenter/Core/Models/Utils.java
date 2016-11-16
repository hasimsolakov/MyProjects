package presenter.Core.Models;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Utils {

    private static final Map<Class, Function<String, Object>> conversions = new HashMap<>();

    public static Map<Class, Function<String, Object>> getConversions(){
        fillInConversions();
        return conversions;
    }

    private static void fillInConversions(){
        conversions.putIfAbsent(int.class, Integer::parseInt);
        conversions.putIfAbsent(Integer.class, Integer::parseInt);
        conversions.putIfAbsent(double.class, Double::parseDouble);
        conversions.putIfAbsent(Double.class, Double::parseDouble);
        conversions.putIfAbsent(long.class, Long::parseLong);
        conversions.putIfAbsent(Long.class, Long::parseLong);
        conversions.put(String.class, String::toString);
    }

}
