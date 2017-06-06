import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Kata info:
 *
 * https://www.codewars.com/kata/51b62bf6a9c58071c600001b
 */
public class Conversion {
    static Map<Integer, String> symbols = new LinkedHashMap<>();
    static {
        symbols.put(1000, "M");
        symbols.put(900, "CM");
        symbols.put(500, "D");
        symbols.put(400, "CD");
        symbols.put(100, "C");
        symbols.put(90, "XC");
        symbols.put(50, "L");
        symbols.put(40, "XL");
        symbols.put(10, "X");
        symbols.put(9, "IX");
        symbols.put(5, "V");
        symbols.put(4, "IV");
        symbols.put(1, "I");
    }

    public String solution(int n) {
        StringBuilder rtn = new StringBuilder("");
        while (n > 0) {
            for (Map.Entry<Integer, String> entry : symbols.entrySet()) {
                if (n >= entry.getKey()) {
                    rtn.append(entry.getValue());
                    n -= entry.getKey();
                    break;
                }
            }
        }
        return rtn.toString();
    }
}