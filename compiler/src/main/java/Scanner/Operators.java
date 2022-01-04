package Scanner;

public enum Operators{
    NOT("(!)"),
    TIMES("([*])"),
    DIV("([/])"),
    MOD("([%])"),
    PLUS("([+])"),
    MINUS("([-])"),
    EQ("(=)"),
    NE("(!=)"),
    GT("(>)"),
    LT("(<)"),
    GE("(>=)"),
    LE("(<=)"),
    BAND(""),
    BOR("");

    public final String pattern;
    Operators(String pattern){
        this.pattern = pattern;
    }


}