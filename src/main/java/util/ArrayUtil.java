package util;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {

    public static List<?> slice(int start, int end, Object... input) {
        List<Object> sliced = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (i < input.length) {
                sliced.add(input[i]);
            } else {
                break;
            }
        }
        return sliced;
    }
}
