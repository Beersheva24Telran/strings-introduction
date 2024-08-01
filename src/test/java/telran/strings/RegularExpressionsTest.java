package telran.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RegularExpressionsTest {
    @Test
    void javaVariableTrueTest() {
        String regex = Strings.javaVariable();

        assertTrue("name".matches(regex));
        assertTrue("a_1".matches(regex));
        assertTrue("S".matches(regex));
        assertTrue("$".matches(regex));
        assertTrue("__".matches(regex)); // two underscore symbols
        assertTrue("ab_cd_$12$".matches(regex));
    }

    @Test
    void javaVariableFalseTest() {
        String regex = Strings.javaVariable();

        assertFalse("1name".matches(regex));
        assertFalse("1_a".matches(regex));
        assertFalse("_".matches(regex));
        assertFalse("#1".matches(regex));
        assertFalse("_*".matches(regex)); // two underscore symbols
        assertFalse("ab_cd_$12?".matches(regex));
        assertFalse("ab_cd_$12 ".matches(regex));
    }

    @Test
    void number0_300TrueTest() {
        String regex = Strings.number0_300();
        assertTrue("0".matches(regex));
        assertTrue("300".matches(regex));
        assertTrue("250".matches(regex));
        assertTrue("25".matches(regex));
        assertTrue("12".matches(regex));
        assertTrue("299".matches(regex));
        assertTrue("1".matches(regex));

    }

    @Test
    void number0_300FalseTest() {
        String regex = Strings.number0_300();
        assertFalse("00".matches(regex));
        assertFalse("301".matches(regex));
        assertFalse("01".matches(regex));
        assertFalse("00".matches(regex));
        assertFalse("1(".matches(regex));
        assertFalse("1000".matches(regex));
        assertFalse(" 20".matches(regex));
        assertFalse("1001".matches(regex));
    }

    @Test
    void ipV4OctetTrueTest() {
        String regex = Strings.ipV4Octet();
        assertTrue("0".matches(regex));
        assertTrue("00".matches(regex));
        assertTrue("000".matches(regex));
        assertTrue("10".matches(regex));
        assertTrue("100".matches(regex));
        assertTrue("255".matches(regex));
        assertTrue("199".matches(regex));
        assertTrue("249".matches(regex));
    }

    @Test
    void ipV4OctetFalseTest() {
        String regex = Strings.ipV4Octet();
        assertFalse("0000".matches(regex));
        assertFalse("t".matches(regex));
        assertFalse("-1".matches(regex));
        assertFalse("1111".matches(regex));
        assertFalse("0001".matches(regex));
        assertFalse("256".matches(regex));
        assertFalse("300".matches(regex));
        assertFalse("*".matches(regex));
        assertFalse("1 ".matches(regex));
    }

    @Test
    void ipV4AddressTrueTest() {
        String regex = Strings.ipV4Address();
        assertTrue("0.0.0.0".matches(regex));
        assertTrue("255.255.255.255".matches(regex));
    }

    @Test
    void ipV4AddressFalseTest() {
        String regex = Strings.ipV4Address();
        assertFalse("0.0.0".matches(regex));
        assertFalse("0.0.0+0".matches(regex));
        assertFalse("0".matches(regex));
        assertFalse("0.-".matches(regex));
        assertFalse("0.0.0*0".matches(regex));
        assertFalse("0.0.0 0".matches(regex));
    }

    @Test
    void stringWithJavaNamesTest() {
        String names = "123 1a _ abs int enum null lmn";
        String expected = "abs lmn";
        assertEquals(expected, Strings.stringWithJavaNames(names));
    }

    @Test
    void isArithmeticExpressionTrueTest() {
        assertTrue(Strings.isArithmeticExpression("((a + b) /2.5) + x"));
        assertTrue(Strings.isArithmeticExpression("a + b/2.5+ x"));
        assertTrue(Strings.isArithmeticExpression("(a + b)/2.5+ (x)"));
        assertTrue(Strings.isArithmeticExpression("(a + b)/2.5+ 120/0 * 27"));
        assertTrue(Strings.isArithmeticExpression("(((a + b)/2.5)+ x)"));
    }

    @Test
    void isArithmeticExpressionFalseTest() {
        assertFalse(Strings.isArithmeticExpression("(((a + b) /2.5) + x"));
        assertFalse(Strings.isArithmeticExpression("a + b/2.5+ _"));
        assertFalse(Strings.isArithmeticExpression("(a + b))/2.5+ (x)"));
        assertFalse(Strings.isArithmeticExpression("(a + b)#2.5+ 120/0 * 27"));
        assertFalse(Strings.isArithmeticExpression("(((a + b)/2 5)+ x)"));
        assertFalse(Strings.isArithmeticExpression("(((a (+) b)/25)+ x)"));
        assertFalse(Strings.isArithmeticExpression("(((a + b)/25)+ int)"));
    }

}
