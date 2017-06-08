package codewars.fmmarzoa.java.conversion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by fmmarzoa on 6/5/17.
 */
public class ConversionTest {
    private Conversion conversion = new Conversion();

    @Test
    public void solution() throws Exception {
        assertEquals("solution(1889) should equal to MDCCCLXXXIX", "MDCCCLXXXIX", conversion.solution(1889));
        assertEquals("solution(4) should equal to IV", "IV", conversion.solution(4));
        assertEquals("solution(6) should equal to VI", "VI", conversion.solution(6));
    }

}