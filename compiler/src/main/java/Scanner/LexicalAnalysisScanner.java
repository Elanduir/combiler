package Scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class LexicalAnalysisScanner {

    private final List<String> tokenDelimiter = List.of(" ", "\n", "!", "*", "/", "%", "+", "-", ":", "!", "<", ">", "=");

    public List<Base> analyze(Path filePath){
        String[] characters = readFile(filePath);
        return generateTokens(characters);
    }

    private String[] readFile(Path filePath){
        String input = "empty";
        try {
            input = Files.readString(filePath);
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return input.split("");
    }

    private List<Base> generateTokens(String[] characters){
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
            newToken = nextIndex == i || newToken;

            boolean isIdent = true;

            if(newToken){
                int startSize = tokenList.size();
                int endSize;
                if(!toMatch.equals("")){
                    if(toMatch.matches(Terminals.LITERAL.pattern)) tokenList.add(new Literal(Integer.parseInt(toMatch)));

                    if(toMatch.matches(Operators.NOT.pattern)) tokenList.add(new RelOpr(Operators.NOT));
                    if(toMatch.matches(Operators.EQ.pattern)) tokenList.add(new RelOpr(Operators.EQ));
                    if(toMatch.matches(Operators.NE.pattern)) tokenList.add(new RelOpr(Operators.NE));
                    if(toMatch.matches(Operators.GT.pattern)) tokenList.add(new RelOpr(Operators.GT));
                    if(toMatch.matches(Operators.LT.pattern)) tokenList.add(new RelOpr(Operators.LT));
                    if(toMatch.matches(Operators.GE.pattern)) tokenList.add(new RelOpr(Operators.GE));
                    if(toMatch.matches(Operators.LE.pattern)) tokenList.add(new RelOpr(Operators.LE));
                    //if(toMatch.matches(Operators.CAND.pattern)) tokenList.add(new Relopr(Operators.CAND));
                    //if(toMatch.matches(Operators.COR.pattern)) tokenList.add(new Relopr(Operators.COR));

                    if(toMatch.matches(Operators.TIMES.pattern)) tokenList.add(new AddOpr(Operators.TIMES));
                    if(toMatch.matches(Operators.DIV.pattern)) tokenList.add(new AddOpr(Operators.DIV));
                    if(toMatch.matches(Operators.MOD.pattern)) tokenList.add(new AddOpr(Operators.MOD));
                    if(toMatch.matches(Operators.PLUS.pattern)) tokenList.add(new AddOpr(Operators.PLUS));
                    if(toMatch.matches(Operators.MINUS.pattern)) tokenList.add(new AddOpr(Operators.MINUS));

                    if(toMatch.equals(Terminals.WHILE.pattern)) tokenList.add(new Base(Terminals.WHILE));
                    if(toMatch.equals(Terminals.ENDWHILE.pattern)) tokenList.add(new Base(Terminals.ENDWHILE));
                    if(toMatch.equals(Terminals.DO.pattern)) tokenList.add(new Base(Terminals.DO));
                    if(toMatch.equals(Terminals.BECOMES.pattern)) tokenList.add(new Base(Terminals.BECOMES));

                    endSize = tokenList.size();
                    isIdent = startSize == endSize;
                    if(isIdent && toMatch.matches(Terminals.IDENT.pattern)) tokenList.add(new Ident(toMatch));
                }




                sBuild = new StringBuilder();


            }

        }
        tokenList.add(new Base(Terminals.SENTINEL));
        return tokenList;
    }
}
