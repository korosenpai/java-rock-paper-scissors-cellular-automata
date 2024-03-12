import java.util.Hashtable;
import java.util.Arrays;


public class Colors {
    public static Hashtable<Integer, int[]> rgbFromInt = new Hashtable<>();

    public static void main(String[] args) {
        rgbFromInt.put(0, new int[]{255, 255, 255});
        System.out.println(Arrays.toString(get(0)));
    }

    public Colors() {
        // experiment with different palettes!

        // rgbFromInt.put(0, new int[]{51, 51, 51});
        // rgbFromInt.put(1, new int[]{72, 229, 194});
        // rgbFromInt.put(2, new int[]{252, 250, 249});

        rgbFromInt.put(0, new int[]{46, 53, 50});
        rgbFromInt.put(1, new int[]{126, 145, 129});
        rgbFromInt.put(2, new int[]{199, 206, 219});

        // rgbFromInt.put(0, new int[]{226, 239, 222});
        // rgbFromInt.put(1, new int[]{175, 208, 191});
        // rgbFromInt.put(2, new int[]{128, 143, 135});
    }

    // if intval is not in hashtable it returns null
    public static int[] get(int intVal) {
        return rgbFromInt.get(intVal);
    }

    public static int size() {
        return rgbFromInt.size();
    }


}
