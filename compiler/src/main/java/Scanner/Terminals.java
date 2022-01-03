package Scanner;

import java.lang.reflect.Type;

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
    SENTINEL(""),
    CASTSTOL("castStoL"),
    CASTSTOI("castStoI"),
    CASTITOS("castItoS"),
    CASTITOL("castItoL"),
    CASTLTOS("castLtoS"),
    CASTLTOI("castLtoI"),
    TYPE("short|int|long");

    public final String pattern;
    Terminals(String pattern){
        this.pattern = pattern;
    }
}