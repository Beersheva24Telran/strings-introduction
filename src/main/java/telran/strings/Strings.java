package telran.strings;


public class Strings {
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
        return "[a-zA-Z$]|[a-zA-Z$_][\\w\\d$]+";
    }
}