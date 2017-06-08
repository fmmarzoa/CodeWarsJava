package codewars.fmmarzoa.java.unknowndigit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fmmarzoa on 6/9/17.
 *
 * Kata description:
 * https://www.codewars.com/kata/546d15cebed2e10334000ed9
 */
public class Runes {
    private static final Pattern expressionPattern = Pattern.compile("(-?\\d+)([+*-])(-?\\d+)");
    private static final Pattern leftZeroesPattern = Pattern.compile("0\\d+");

    private static class LeftZeroesException extends Exception {
    }

    public static class InvalidMathExpression extends Exception {
        InvalidMathExpression(String s) {
            super(s);
        }
    }

    private static int evalExpression(String expression) throws LeftZeroesException, InvalidMathExpression {
        Matcher expressionMatcher = expressionPattern.matcher(expression);
        if (expressionMatcher.matches()) {
            String expLeft = expressionMatcher.group(1);
            String expRight = expressionMatcher.group(3);
            Matcher leftZeroesMatcher = leftZeroesPattern.matcher(expLeft);
            if (leftZeroesMatcher.matches())
                throw new LeftZeroesException(); // Left 0s are not allowed
            leftZeroesMatcher = leftZeroesPattern.matcher(expRight);
            if (leftZeroesMatcher.matches())
                throw new LeftZeroesException(); // Left 0s are not allowed
            String expOperator = expressionMatcher.group(2);

            int leftVal = Integer.parseInt(expLeft);
            int rightVal = Integer.parseInt(expRight);
            switch (expOperator) {
                case "+":
                    return leftVal + rightVal;
                case "-":
                    return leftVal - rightVal;
                case "*":
                    return leftVal * rightVal;
            }

//            System.out.format("%s %s %s", expLeft, expOperator, expRight);
        } else {
            throw new InvalidMathExpression("Illegal expression: " + expression);
        }
        return -1;
    }

    public static int solveExpression(final String expression) throws InvalidMathExpression {
        // TODO: Check the digits to avoid using those in that are already in the expression, following the rules:
        // "All of the ?s in an expression will represent the same digit (0-9), and it won't be one of the other given digits in the expression"
        int missingDigit = -1;
        for (int digit = 0; digit <= 9; digit++) {
            String exp = expression.replace('?', Character.forDigit(digit, 10));
            String[] expParts = exp.split("=");
            Matcher leftZeroesMatcher = leftZeroesPattern.matcher(expParts[1]);
            if (leftZeroesMatcher.matches())
                continue; // left 0s are not valid, so skip to next iteration
            int actualResult;
            try {
                actualResult = evalExpression(expParts[0]);
            } catch (LeftZeroesException e) {
                // left 0s not valid, so skip to next iteration
                continue;
            }
            int expectedResult = Integer.parseInt(expParts[1]);
            if (actualResult == expectedResult) {
                missingDigit = digit;
                break;
            }
        }
        return missingDigit;
    }
}