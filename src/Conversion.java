public class Conversion {
    public String solution(int n) {
        StringBuilder rtn = new StringBuilder("");
        while (n > 0) {
            if (n >= 1000) {
                rtn.append("M");
                n -= 1000;
            } else {
                if (n >= 900) {
                    rtn.append("CM");
                    n -= 900;
                } else if (n >= 500) {
                    rtn.append("D");
                    for (int i = 500; i < n; i += 100) {
                        rtn.append("C");
                        n -= 100;
                    }
                    n -= 500;
                } else if (n >= 400) {
                    rtn.append("CD");
                    n -= 400;
                } else if (n >= 100) {
                    for (int i = 100; i < n; i += 100) {
                        rtn.append("C");
                        n -= 100;
                    }
                } else if (n >= 90) {
                    rtn.append("XC");
                    n -= 90;
                } else if (n >= 50) {
                    rtn.append("L");
                    for (int i = 50; i < n; i += 10) {
                        rtn.append("X");
                        n -= 10;
                    }
                    n -= 50;
                } else if (n >= 40) {
                    rtn.append("XL");
                    n -= 40;
                } else if (n >= 10) {
                    for (int i = 10; i < n; i += 10) {
                        rtn.append("X");
                        n -= 10;
                    }
                } else if (n == 9) {
                    rtn.append("IX");
                    n -= 9;
                } else if (n >= 5) {
                    rtn.append("V");
                    for (int i = 5; i < n; i += 1) {
                        rtn.append("I");
                        n -= 1;
                    }
                    n -= 5;
                } else if (n == 4) {
                    rtn.append("IV");
                    n -= 4;
                } else if (n >= 1) {
                    rtn.append("I");
                    for (int i = 1; i < n; i += 1) {
                        rtn.append("I");
                        n -= 1;
                    }
                    n -= 1;
                }
            }
        }
        return rtn.toString();
    }
}