package Scanner;

import java.lang.reflect.Type;

public enum Terminals {
    PROGRAM("(program)"),
    ENDPROGRAM("(endprogram)"),
    GLOBAL("(global)"),
    FUNCTION("(fun)"),
    PROCEDUR("proc"),
    ENDPROCEDUR("endproc"),
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
    MULTOPR(""),
    BOOLOPR("bAnd|bOr"),
    LITERAL("[0-9]+"),
    SENTINEL(""),
    CASTSTOL("castStoL"),
    CASTSTOI("castStoI"),
    CASTITOS("castItoS"),
    CASTITOL("castItoL"),
    CASTLTOS("castLtoS"),
    CASTLTOI("castLtoI"),
    TYPE("short|int|long"),
    FLOWMODE("in|out|inout"),
    CHANGEMODE("var|const"),
    MECHMODE("copy|ref"),
    RETURNS("returns"),
    ENDFUN("endfun"),
    LOCAL("local"),
    COMMA(","),
    IF("if"),
    THEN("then"),
    ELSE("else"),
    ENDIF("endif"),
    CALL("call"),
    NOT("not"),
    SKIP("skip"),
    INIT("init");

    public final String pattern;
    Terminals(String pattern){
        this.pattern = pattern;
    }
}