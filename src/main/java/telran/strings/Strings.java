package telran.strings;

import java.util.Arrays;
import java.util.regex.*;

import javax.lang.model.SourceVersion;

public class Strings {
    static Pattern pattern;
    static {
        pattern = Pattern.compile(getArithmeticRegex());
    }
    static final String keyWords[] = { "abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum", "extends", "false",
                "final", "finally", "float", "for", "goto", "if", "implements",
                "import", "instanceof", "int", "interface", "long", "native",
                "new", "null", "package", "private", "protected", "public",
                "return", "short", "static", "strictfp", "super", "switch",
                "synchronized", "this", "throw", "throws", "transient", "true",
                "try", "void", "volatile", "while" };
    public static String firstName() {
        // regex for strings startin with
        // capital letter and rest as a
        // lower case letters
        // minimal length is 5 letters
        return "[A-Z][a-z]{4,}";
    }

    public static String javaVariable() {
        // regular expression for testing synthax
        // of Java variable names
        return "((?!_$)[a-zA-Z$_][\\w$]*)";
    }
    public static String number0_300() {
       
        return "[1-9]\\d?|[1-2]\\d\\d|300|0";
    }
    public static String ipV4Octet(){
        
        return "([0-1]?\\d{1,2}|2([0-4]\\d|5[0-5]))";
    }
    public static String ipV4Address(){
        String octetExpr = ipV4Octet();
        return String.format("%s(\\.%s){3}", octetExpr, octetExpr);
    }
    public static String stringWithJavaNames(String names) {
       String [] tokens = names.split("\\s+");
       int i = getJavaNameIndex(tokens, -1);
       String res = "";
       if (i >= 0) {
         res = tokens[i];
       }
       while((i = getJavaNameIndex(tokens, i)) > 0) {
            res += " " + tokens[i];
       }
       
        return res;
    }

    private static int getJavaNameIndex(String[] tokens, int i) {
        i++;
        while(i < tokens.length && !isJavaName(tokens[i])) {
            i++;
        }
        return i < tokens.length ? i : -1;
    }

    private static boolean isJavaName(String string) {
        
        return string.matches(javaVariable()) && !isJavaKeyword(string);
}

    private static boolean isJavaKeyword(String string) {
        return SourceVersion.isKeyword(string);
    }
public static boolean isArithmeticExpression(String expr) {
    //1. brackets
    //right position of open / close bracket is matter of regex
    //matching between open and close bracket is matter of the method you are supposed to write
    //based on a counter. If counter is negative - no matching;
    // if at ending up going through a string the counter doesn't equal 0 - no matching
    //matching may be only in one case: at the ending up of going the counter will be 0
    // Operator - regular expression for one out of 4 arithemetic operators [*/+-]
    //Operand may be either Java variable name or number (better any)
    
    Matcher matcher = pattern.matcher(expr);
    boolean exprMatch = matcher.matches();
    boolean pairness = pairnessCheck(expr);
    boolean javaNames = javaNamesCheck(expr);
    return  exprMatch && pairness && javaNames;
}

private static boolean javaNamesCheck(String expr) {
    String[] operands = expr.split(getRegexForOperandsSep());
    int index = 0;
    while(index < operands.length && !isJavaKeyword(operands[index])) {
        index++;
    }
    return index == operands.length;
}

private static String getRegexForOperandsSep() {
    return String.format("%s|[\\s()]+", getOperatorRegex());
}

private static String getOperatorRegex() {
    return "[+/*-]";
}

private static boolean pairnessCheck(String expr) {
    char[] exprChars = expr.toCharArray();
    int index = 0;
    int counter = 0;
    while(index < exprChars.length && counter >= 0) {
        if (exprChars[index] == '(') {
            counter++;
        } else if(exprChars[index] == ')') {
            counter--;
        }
        index++;
    }
    return counter == 0;
}

private static String getArithmeticRegex() {
    String operator = getOperatorRegex();
    String operand = getOperandRegex();
    String regex = String.format("%s(%s%s)*", operand, operator, operand);
    return regex;
}

private static String getOperandRegex() {
    String number = getNumberRegex();
    String regex = String.format("[\\s(]*(%s|%s)[\\s)]*", number, javaVariable());
    return regex;
}

private static String getNumberRegex() {
    return "\\d+\\.?\\d*|\\.\\d+";
}
}