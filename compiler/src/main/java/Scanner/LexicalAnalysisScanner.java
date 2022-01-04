package Scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class LexicalAnalysisScanner {

    private final List<String> tokenDelimiter = List.of(" ", "\n", "!", "*", "/", "%", "+", "-", ":", "!", "<", ">", "=", ";", "(", ")", ",");

    public List<Base> analyze(Path filePath) throws LexicalAnalysisException {
        String[] characters = readFile(filePath);
        return generateTokens(characters);
    }

    // Reads the IML file into a String array
    private String[] readFile(Path filePath){
        String input = "empty";
        try {
            input = Files.readString(filePath);
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return input.split("");
    }

    // Uses the tokenDelimiter list to generate the different elements and matches them with the corresponding token
    private List<Base> generateTokens(String[] characters) throws LexicalAnalysisException {
        StringBuilder sBuild  = new StringBuilder();
        List<Base> tokenList = new ArrayList<>();
        String toMatch;


        for (int i = 0; i < characters.length; i++) {
            sBuild.append(characters[i]);
            toMatch = sBuild.toString().trim();

            int nextIndex = (i + 1 < characters.length) ? i + 1 : i;
            String currentChar = characters[i];
            String nextChar = characters[nextIndex];

            boolean currentDelimiter = tokenDelimiter.stream().anyMatch(currentChar::equals);
            boolean nextDelimiter = tokenDelimiter.stream().anyMatch(nextChar::equals);

            boolean newToken = (currentDelimiter && !nextDelimiter) || (!currentDelimiter && nextDelimiter);

            // Checks for multiple brackets and splits them in multiple tokens
            if(currentChar.matches(Terminals.LEFTRBRACKET.pattern) && nextChar.matches(Terminals.RIGHTRBRACKET.pattern)) newToken = true;
            if(currentChar.matches(Terminals.RIGHTRBRACKET.pattern) && nextChar.matches(Terminals.RIGHTRBRACKET.pattern)) newToken = true;
            if(currentChar.matches(Terminals.LEFTRBRACKET.pattern) && nextChar.matches(Terminals.LEFTRBRACKET.pattern)) newToken = true;
            if(currentChar.matches(Terminals.RIGHTRBRACKET.pattern) && nextChar.matches(Terminals.SEMICOLON.pattern)) newToken = true;

            newToken = nextIndex == i || newToken;

            // boolean to check if Ident
            boolean isIdent;

            if(newToken){
                int startSize = tokenList.size();
                int endSize;
                if(!toMatch.equals("")){

                    //Literal
                    if(toMatch.matches(Terminals.LITERAL.pattern)) tokenList.add(new Literal(Integer.parseInt(toMatch)));

                    //RelOpr
                    if(toMatch.matches(Operators.NOT.pattern)) tokenList.add(new RelOpr(Operators.NOT));
                    if(toMatch.matches(Operators.EQ.pattern)) tokenList.add(new RelOpr(Operators.EQ));
                    if(toMatch.matches(Operators.NE.pattern)) tokenList.add(new RelOpr(Operators.NE));
                    if(toMatch.matches(Operators.GT.pattern)) tokenList.add(new RelOpr(Operators.GT));
                    if(toMatch.matches(Operators.LT.pattern)) tokenList.add(new RelOpr(Operators.LT));
                    if(toMatch.matches(Operators.GE.pattern)) tokenList.add(new RelOpr(Operators.GE));
                    if(toMatch.matches(Operators.LE.pattern)) tokenList.add(new RelOpr(Operators.LE));
                    //if(toMatch.matches(Operators.CAND.pattern)) tokenList.add(new Relopr(Operators.CAND));
                    //if(toMatch.matches(Operators.COR.pattern)) tokenList.add(new Relopr(Operators.COR));

                    //AddOpr
                    if(toMatch.matches(Operators.TIMES.pattern)) tokenList.add(new MultOpr(Operators.TIMES));
                    if(toMatch.matches(Operators.DIV.pattern)) tokenList.add(new AddOpr(Operators.DIV));
                    if(toMatch.matches(Operators.MOD.pattern)) tokenList.add(new AddOpr(Operators.MOD));
                    if(toMatch.matches(Operators.PLUS.pattern)) tokenList.add(new AddOpr(Operators.PLUS));
                    if(toMatch.matches(Operators.MINUS.pattern)) tokenList.add(new AddOpr(Operators.MINUS));
                    if(toMatch.matches(Terminals.BOOLOPR.pattern)) tokenList.add(new BoolOpr(toMatch));

                    //Terminals
                    if(toMatch.matches(Terminals.WHILE.pattern)) tokenList.add(new Base(Terminals.WHILE));
                    if(toMatch.matches(Terminals.ENDWHILE.pattern)) tokenList.add(new Base(Terminals.ENDWHILE));
                    if(toMatch.matches(Terminals.DO.pattern)) tokenList.add(new Base(Terminals.DO));
                    if(toMatch.matches(Terminals.BECOMES.pattern)) tokenList.add(new Base(Terminals.BECOMES));
                    if(toMatch.matches(Terminals.LEFTRBRACKET.pattern)) tokenList.add(new Base(Terminals.LEFTRBRACKET));
                    if(toMatch.matches(Terminals.RIGHTRBRACKET.pattern)) tokenList.add(new Base(Terminals.RIGHTRBRACKET));
                    if(toMatch.matches(Terminals.COLON.pattern)) tokenList.add(new Base(Terminals.COLON));
                    if(toMatch.matches(Terminals.SEMICOLON.pattern)) tokenList.add(new Base(Terminals.SEMICOLON));
                    if(toMatch.matches(Terminals.PROGRAM.pattern)) tokenList.add(new Base(Terminals.PROGRAM));
                    if(toMatch.matches(Terminals.ENDPROGRAM.pattern)) tokenList.add(new Base(Terminals.ENDPROGRAM));
                    if(toMatch.matches(Terminals.GLOBAL.pattern)) tokenList.add(new Base(Terminals.GLOBAL));
                    if(toMatch.matches(Terminals.FUNCTION.pattern)) tokenList.add(new Base(Terminals.FUNCTION));
                    if(toMatch.matches(Terminals.PROCEDUR.pattern)) tokenList.add(new Base(Terminals.PROCEDUR));
                    if(toMatch.matches(Terminals.ENDPROCEDUR.pattern)) tokenList.add(new Base(Terminals.ENDPROCEDUR));
                    if(toMatch.matches(Terminals.DEBUGIN.pattern)) tokenList.add(new Base(Terminals.DEBUGIN));
                    if(toMatch.matches(Terminals.DEBUGOUT.pattern)) tokenList.add(new Base(Terminals.DEBUGOUT));

                    if(toMatch.matches(Terminals.RETURNS.pattern)) tokenList.add(new Base(Terminals.RETURNS));
                    if(toMatch.matches(Terminals.ENDFUN.pattern)) tokenList.add(new Base(Terminals.ENDFUN));
                    if(toMatch.matches(Terminals.LOCAL.pattern)) tokenList.add(new Base(Terminals.LOCAL));
                    if(toMatch.matches(Terminals.COMMA.pattern)) tokenList.add(new Base(Terminals.COMMA));

                    if(toMatch.matches(Terminals.IF.pattern)) tokenList.add(new Base(Terminals.IF));
                    if(toMatch.matches(Terminals.THEN.pattern)) tokenList.add(new Base(Terminals.THEN));
                    if(toMatch.matches(Terminals.ELSE.pattern)) tokenList.add(new Base(Terminals.ELSE));
                    if(toMatch.matches(Terminals.ENDIF.pattern)) tokenList.add(new Base(Terminals.ENDIF));

                    if(toMatch.matches(Terminals.CALL.pattern)) tokenList.add(new Base(Terminals.CALL));
                    if(toMatch.matches(Terminals.NOT.pattern)) tokenList.add(new Base(Terminals.NOT));
                    if(toMatch.matches(Terminals.SKIP.pattern)) tokenList.add(new Base(Terminals.SKIP));
                    if(toMatch.matches(Terminals.INIT.pattern)) tokenList.add(new Base(Terminals.INIT));

                    if(toMatch.matches(Terminals.CASTSTOI.pattern)) tokenList.add(new Base(Terminals.CASTSTOI));
                    if(toMatch.matches(Terminals.CASTSTOL.pattern)) tokenList.add(new Base(Terminals.CASTSTOL));
                    if(toMatch.matches(Terminals.CASTITOS.pattern)) tokenList.add(new Base(Terminals.CASTITOS));
                    if(toMatch.matches(Terminals.CASTITOL.pattern)) tokenList.add(new Base(Terminals.CASTITOL));
                    if(toMatch.matches(Terminals.CASTLTOS.pattern)) tokenList.add(new Base(Terminals.CASTLTOS));
                    if(toMatch.matches(Terminals.CASTLTOI.pattern)) tokenList.add(new Base(Terminals.CASTLTOI));


                    //Types
                    if(toMatch.matches(Terminals.TYPE.pattern)) tokenList.add(new Type(toMatch));

                    //Modes
                    if(toMatch.matches(Terminals.CHANGEMODE.pattern)) tokenList.add(new Changemode(toMatch));
                    if(toMatch.matches(Terminals.FLOWMODE.pattern)) tokenList.add(new Flowmode(toMatch));
                    if(toMatch.matches(Terminals.MECHMODE.pattern)) tokenList.add(new Mechmode(toMatch));

                    //check if it was any of the other tokens -> add as IDENT if it matches throws error if it doesn't
                    endSize = tokenList.size();
                    isIdent = startSize == endSize;
                    if(isIdent && toMatch.matches(Terminals.IDENT.pattern)){
                        tokenList.add(new Ident(toMatch));
                    }else if(isIdent){
                        throw new LexicalAnalysisException("Token \"" + toMatch + "\" is  not a valid input.");
                    }
                }

                //Clear StringBuilder
                sBuild = new StringBuilder();
            }
        }
        tokenList.add(new Base(Terminals.SENTINEL));
        return tokenList;
    }
}
