package codewars.fmmarzoa.java.longestslidedown;

/**
 * Kata info:
 *
 * https://www.codewars.com/kata/551f23362ff852e2ab000037
 */
public class LongestSlideDown {
    public static int longestSlideDown(int[][] pyramid) {
        for (int row = pyramid.length - 1; row > 0; row--) {
            for (int col = 0; col < pyramid[row].length - 1; col++) {
                int left = pyramid[row][col];
                int right = pyramid[row][col + 1];
                int top = pyramid[row - 1][col];
                int newTop = (left > right) ? top + left : top + right;
                pyramid[row - 1][col] = newTop;
            }
        }
        return pyramid[0][0];
    }
}
