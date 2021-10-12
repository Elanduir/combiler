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
    CAND("/\\"),
    COR("\\/");

    public final String pattern;
    Operators(String pattern){
        this.pattern = pattern;
    }


}