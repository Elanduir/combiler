public enum Terminals {
    WHILE("while"),
    ENDWHILE("endwhile"),
    DO("do"),
    BECOMES(":="),
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