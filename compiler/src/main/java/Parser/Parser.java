package Parser;

import Scanner.Base;
import Scanner.LexicalAnalysisScanner.*;
import Scanner.Terminals;

import java.util.List;

public class Parser {

    private List<Base> tokens;
    private Base currentToken;
    private int counter;
    private Terminals currentTerminal;

    public Parser(List<Base> tokens) {
        this.tokens = tokens;
        nextToken();
    }

    private void nextToken() {
        currentToken = tokens.get(counter++);
        currentTerminal = currentToken.getTerminal();
    }

    //consume
    private Base consume(Terminals expectedTerminal) throws GrammerException {
        if (this.currentTerminal == expectedTerminal) {
            Base consumedTerminal = this.currentToken;
            if (this.currentTerminal != Terminals.SENTINEL) {
                nextToken();
            }
            return consumedTerminal;
        } else {
            throw new GrammerException("terminal expected: " + expectedTerminal + ", terminal found: " + currentTerminal);
        }
    }

    //parse


}
