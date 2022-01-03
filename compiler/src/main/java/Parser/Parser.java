package Parser;

import Scanner.Base;
import Scanner.Terminals;
import concSyn.Interfaces.*;

import java.util.List;

public class Parser {

    private List<Base> tokens;
    private Base currentToken;
    private int counter = 0;
   private Terminals currentTerminal;


    public Parser(List<Base> tokens) throws GrammerException {
        this.tokens = tokens;
        nextToken();
        program();
    }

    private void nextToken() {
        currentToken = tokens.get(counter++);
        currentTerminal = currentToken.getTerminal();
    }

    private Base consume(Terminals expectedTerminal) throws GrammerException{
        if(currentTerminal == expectedTerminal){
            Base consumedToken = currentToken;
            if(currentTerminal != Terminals.SENTINEL){
                nextToken();
            }
            return consumedToken;
        }else{
            throw new GrammerException("terminal expected: " + expectedTerminal + ", terminal found: " + currentTerminal);
        }
    }

    private void program() throws GrammerException {
        consume(Terminals.PROGRAM);
        consume(Terminals.IDENT);
        globalNTS();
        consume(Terminals.DO);
        cpsCmd();
        consume(Terminals.ENDPROGRAM);

    }

    private void globalNTS() throws GrammerException{
        if(currentTerminal == Terminals.GLOBAL){
            consume(Terminals.GLOBAL);
            cpsDecl();
        }
    }

    private void cpsDecl() throws GrammerException{
        if(currentTerminal == Terminals.FUNCTION || currentTerminal == Terminals.IDENT){
            decl();
            cpsDeclNTS();
        }

    }

    private void decl() throws  GrammerException{
        stoDecl();
    }

    private void cpsDeclNTS() throws  GrammerException{
        if(currentTerminal == Terminals.SEMICOLON){
            decl();
            cpsDeclNTS();
        }
    }

    private void stoDecl() throws GrammerException{

    }

    private void funDecl() throws GrammerException{

    }

    private void cpsCmd() throws  GrammerException{

    }







    //parse


}
