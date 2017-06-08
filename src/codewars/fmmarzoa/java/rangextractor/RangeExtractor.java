package codewars.fmmarzoa.java.rangextractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Kata info:
 *
 * https://www.codewars.com/kata/51ba717bb08c1cd60f00002f
 */
public class RangeExtractor {
    public static String rangeExtraction(int[] arr) {
        StringBuilder rtn = new StringBuilder();
        List<String> stub = new ArrayList<>();
        stub.add(Integer.toString(arr[0]));
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(arr[i] - arr[i-1]) > 1) {
                makeRange(stub, rtn);
                rtn.append(',');
                stub.clear();
            }
            stub.add(Integer.toString(arr[i]));
        }
        makeRange(stub, rtn);
        return rtn.toString();
    }

    private static void makeRange(List<String> stub, StringBuilder rtn) {
        if (stub.size() > 2) {
            rtn.append(stub.get(0));
            rtn.append('-');
            rtn.append(stub.get(stub.size() - 1));
        } else {
            rtn.append(String.join(",", stub));
        }
    }
}