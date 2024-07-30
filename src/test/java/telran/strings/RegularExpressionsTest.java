package telran.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RegularExpressionsTest {
    @Test
    void javaVariableTrueTest(){
        String regex = Strings.javaVariable();
        
        assertTrue("name".matches(regex));
        assertTrue("a_1".matches(regex));
        assertTrue("S".matches(regex));
        assertTrue("$".matches(regex));
        assertTrue("__".matches(regex)); //two underscore symbols
        assertTrue("ab_cd_$12$".matches(regex));
    }
    @Test
    void javaVariableFalseTest(){
        String regex = Strings.javaVariable();
        
        assertFalse("1name".matches(regex));
        assertFalse("1_a".matches(regex));
        assertFalse("_".matches(regex));
        assertFalse("#1".matches(regex));
        assertFalse("_*".matches(regex)); //two underscore symbols
        assertFalse("ab_cd_$12?".matches(regex));
        assertFalse("ab_cd_$12 ".matches(regex));
    }
}
