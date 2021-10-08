package Scanner;

public enum Terminals {
    PROGRAM("(program)"),
    ENDPROGRAM("(endprogram)"),
    GLOBAL("(global)"),
    FUNCTION("(fun)"),
    DEBUGIN("(debugin)"),
    DEBUGOUT("(debugout)"),
    WHILE("(while)"),
    ENDWHILE("(endwhile)"),
    DO("(do)"),
    BECOMES("(:=)"),
    LEFTRBRACKET("([(])"),
    RIGHTRBRACKET("([)])"),
    COLON("(:)"),
    SEMICOLON("(;)"),
    IDENT("([a-zA-Z0-9]+)"),
    RELOPR(""),
    ADDOPR(""),
    LITERAL("[0-9]+"),
    SENTINEL("");

    public final String pattern;
    Terminals(String pattern){
        this.pattern = pattern;
    }
}