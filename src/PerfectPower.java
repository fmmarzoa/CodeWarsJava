/**
 * Kata info:
 *
 * https://www.codewars.com/kata/54d4c8b08776e4ad92000835
 */
public class PerfectPower {
    public static double getLog(int number, int base) {
        return Math.log(number) / Math.log(base);
    }

    public static int[] isPerfectPower(int n) {
        if (n==0)
            return null;
        int[] rtn = new int[2];
        int base = 2;
        do {
            long logRounded = Math.round(getLog(n, base));
            if ( (logRounded != 0) && (logRounded != 1) && (Math.pow(base, logRounded) == n)) {
                rtn[0] = base;
                rtn[1] = (int)logRounded;
                return rtn;
            } else {
                base++;
            }
        } while (Math.pow(base, 2) <= n);
        return null;
    }
}
