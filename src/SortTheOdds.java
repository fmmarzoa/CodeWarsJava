import java.util.Arrays;

public class SortTheOdds {
    public static int[] sortArray(int[] array) {
        boolean changed = true;
        while (changed) {
            int left = 0;
            int right = array.length - 1;
            changed = false;
            while (right > left) {
                System.out.println ("array: " + Arrays.toString(array));
                System.out.format ("left: %d right: %d\n", left, right);
                System.out.format ("array[left]: %d array[right]: %d\n", array[left], array[right]);
                if ( array[left] % 2 == 0 ) {
                    left++;
                } else if (array[right] % 2 == 0) {
                    right--;
                } else {
                    if ( array[left] > array[right] ) {
                        int aux = array[left];
                        array[left] = array[right];
                        array[right] = aux;
                        left++;
                        right--;
                        changed = true;
                    } else {
                        left++;
                    }
                }
            }
        }
        System.out.println("Output: " + Arrays.toString(array));
        return array;
    }
}
