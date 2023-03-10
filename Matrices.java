import java.util.*;
public class Matrices {
    public static String toString(Object[][] matrix) {
        StringBuilder builder = new StringBuilder();
        for (Object[] obj : matrix) {
            builder.append(Arrays.toString(obj)).append("\n");
        }
        return builder.toString();
    }
}