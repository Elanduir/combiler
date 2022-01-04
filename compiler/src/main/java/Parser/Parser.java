package Parser;

import Scanner.Base;
import Scanner.Terminals;
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
            System.out.println("Consumed: " + currentTerminal + " " + currentToken);
            return consumedToken;
        }else{
            throw new GrammerException("terminal expected: " + expectedTerminal + ", terminal found: " + currentTerminal + currentToken);
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
        if(currentTerminal == Terminals.FUNCTION || currentTerminal == Terminals.IDENT || currentTerminal == Terminals.PROCEDUR || currentTerminal == Terminals.CHANGEMODE){
            decl();
            cpsDeclNTS();
        }

    }

    private void decl() throws  GrammerException{
        if( currentTerminal == Terminals.IDENT ||  currentTerminal == Terminals.CHANGEMODE){
            stoDecl();
        }else if(currentTerminal == Terminals.FUNCTION){
            funDecl();
        }else{
            procDecl();
        }
    }

    private void cpsDeclNTS() throws  GrammerException{
        if(currentTerminal == Terminals.SEMICOLON){
            consume(Terminals.SEMICOLON);
            decl();
            cpsDeclNTS();
        }
    }

    private void stoDecl() throws GrammerException{
        if(currentTerminal == Terminals.CHANGEMODE){
            consume(Terminals.CHANGEMODE);
            typeident();
        }else{
            typeident();
        }
    }

    private void funDecl() throws GrammerException{
        consume(Terminals.FUNCTION);
        consume(Terminals.IDENT);
        paramList();
        consume(Terminals.RETURNS);
        stoDecl();
        funDeclNTS();
        consume(Terminals.DO);
        cpsCmd();
        consume(Terminals.ENDFUN);
    }

    private void funDeclNTS() throws GrammerException{
        if(currentTerminal == Terminals.LOCAL){
            consume(Terminals.LOCAL);
            cpsStoDecl();
        }
    }

    private void cpsStoDecl() throws GrammerException{
        stoDecl();
        cpsStoDeclNTS();
    }

    private void cpsStoDeclNTS() throws  GrammerException {
        if(currentTerminal == Terminals.SEMICOLON){
            consume(Terminals.SEMICOLON);
            stoDecl();
            cpsStoDeclNTS();
        }
    }

    private void procDecl() throws GrammerException{
        consume(Terminals.PROCEDUR);
        consume(Terminals.IDENT);
        paramList();
        procDeclNTS();
        consume(Terminals.DO);
        cpsCmd();
        consume(Terminals.ENDPROCEDUR);
    }

    private  void procDeclNTS() throws GrammerException {
        if(currentTerminal == Terminals.LOCAL){
            consume(Terminals.LOCAL);
            cpsStoDecl();
        }
    }

    private void cpsCmd() throws  GrammerException{
        if(currentTerminal == Terminals.DEBUGOUT || currentTerminal == Terminals.DEBUGIN || currentTerminal == Terminals.CALL || currentTerminal == Terminals.WHILE || currentTerminal == Terminals.IF || currentTerminal == Terminals.LEFTRBRACKET || currentTerminal == Terminals.ADDOPR || currentTerminal == Terminals.NOT || currentTerminal == Terminals.IDENT || currentTerminal == Terminals.LITERAL || currentTerminal == Terminals.SKIP){
            cmd();
            cpsCmdNTS();
        }
    }

    private void cpsCmdNTS() throws GrammerException {
        if(currentTerminal == Terminals.SEMICOLON){
            consume(Terminals.SEMICOLON);
            cmd();
            cpsCmdNTS();
        }
    }

    private void cmd() throws GrammerException {
        if(currentTerminal == Terminals.LEFTRBRACKET || currentTerminal == Terminals.ADDOPR || currentTerminal == Terminals.NOT || currentTerminal == Terminals.IDENT || currentTerminal == Terminals.LITERAL){
            expr();
            consume(Terminals.BECOMES);
            expr();
        }else if(currentTerminal == Terminals.SKIP){
            consume(Terminals.SKIP);
        }else if(currentTerminal == Terminals.IF){
            consume(Terminals.IF);
            expr();
            consume(Terminals.THEN);
            cpsCmd();
            ifelseNTS();
            consume(Terminals.ENDIF);
        }else if(currentTerminal == Terminals.WHILE){
            consume(Terminals.WHILE);
            expr();
            consume(Terminals.DO);
            cpsCmd();
            consume(Terminals.ENDWHILE);
        }else if(currentTerminal == Terminals.DEBUGIN){
            consume(Terminals.DEBUGIN);
            expr();
        }else if(currentTerminal == Terminals.DEBUGOUT){
            consume(Terminals.DEBUGOUT);
            expr();
        }else if(currentTerminal == Terminals.CALL){
            consume(Terminals.CALL);
            consume(Terminals.IDENT);
            exprList();
        }
    }

    private void ifelseNTS() throws GrammerException{
        if(currentTerminal == Terminals.ELSE){
            consume(Terminals.ELSE);
            cpsCmd();
        }
    }

    private void expr() throws GrammerException {
        if(currentTerminal == Terminals.LEFTRBRACKET || currentTerminal == Terminals.ADDOPR || currentTerminal == Terminals.NOT || currentTerminal == Terminals.IDENT || currentTerminal == Terminals.LITERAL){
            term0();
        }
    }

    //region terms for everything
    public void term0() throws  GrammerException {
        term1();
        term0NTS();
    }

    public void term0NTS() throws  GrammerException {
        if(currentTerminal == Terminals.BOOLOPR){
            consume(Terminals.BOOLOPR);
            term1();
            term0NTS();
        }
    }

    public void term1() throws  GrammerException {
        if(currentTerminal == Terminals.LEFTRBRACKET || currentTerminal == Terminals.ADDOPR || currentTerminal == Terminals.NOT || currentTerminal == Terminals.IDENT || currentTerminal == Terminals.LITERAL) {
        }
        term2();
        term1NTS();
    }

    public void term1NTS() throws  GrammerException {
        if(currentTerminal == Terminals.RELOPR){
            consume(Terminals.RELOPR);
            term2();
        }
    }

    public void term2() throws  GrammerException {
        term3();
        term2NTS();
    }

    public void term2NTS() throws  GrammerException {
        if(currentTerminal == Terminals.ADDOPR){
            consume(Terminals.ADDOPR);
            term3();
            term2NTS();
        }
    }

    public void term3() throws  GrammerException {
        term4();
        term3NTS();
    }

    public void term3NTS() throws  GrammerException {
        if(currentTerminal == Terminals.CASTSTOI){
            consume(Terminals.CASTSTOI);
            term4();
            term3NTS();
        }
    }

    public void term4() throws  GrammerException {
        term5();
        term4NTS();
    }

    public void term4NTS() throws  GrammerException {
        if(currentTerminal == Terminals.CASTSTOL){
            consume(Terminals.CASTSTOL);
            term5();
            term4NTS();
        }
    }

    public void term5() throws  GrammerException {
        term6();
        term5NTS();
    }

    public void term5NTS() throws  GrammerException {
        if(currentTerminal == Terminals.CASTITOS){
            consume(Terminals.CASTITOS);
            term6();
            term5NTS();
        }
    }

    public void term6() throws  GrammerException {
        term7();
        term6NTS();
    }

    public void term6NTS() throws  GrammerException {
        if(currentTerminal == Terminals.CASTITOL){
            term7();
            term6NTS();
        }
    }

    public void term7() throws  GrammerException {
        term8();
        term7NTS();
    }

    public void term7NTS() throws  GrammerException {
        if(currentTerminal == Terminals.CASTLTOS){
            term8();
            term7NTS();
        }
    }

    public void term8() throws  GrammerException {
        term9();
        term8NTS();
    }

    public void term8NTS() throws  GrammerException {
        if(currentTerminal == Terminals.CASTLTOI){
            consume(Terminals.CASTLTOI);
            term9();
            term8NTS();
        }
    }

    public void term9() throws  GrammerException {
        factor();
        term9NTS();
    }

    public void term9NTS() throws  GrammerException {
        if(currentTerminal == Terminals.MULTOPR){
            consume(Terminals.MULTOPR);
            factor();
            term9NTS();
        }
    }


    //endregion

    public void factor() throws GrammerException {
        if(currentTerminal == Terminals.IDENT){
            consume(Terminals.IDENT);
            factorNTS();
        }else if(currentTerminal == Terminals.LEFTRBRACKET){
            consume(Terminals.LEFTRBRACKET);
            expr();
            consume(Terminals.RIGHTRBRACKET);
        }else if(currentTerminal == Terminals.LITERAL){
            consume(Terminals.LITERAL);
        }else{
            monadicOpr();
            factorNTS();
        }
    }

    public void factorNTS() throws GrammerException {
        if(currentTerminal == Terminals.INIT){
            consume(Terminals.INIT);
        }else if(currentTerminal == Terminals.LEFTRBRACKET){
            exprList();
        }
    }

    public void monadicOpr() throws GrammerException {
        if(currentTerminal == Terminals.ADDOPR){
            consume(Terminals.ADDOPR);
        }else{
            consume(Terminals.NOT);
        }
    }



    private void exprList() throws GrammerException {
        consume(Terminals.LEFTRBRACKET);
        exprListparentNTS();
        consume(Terminals.RIGHTRBRACKET);

    }

    private void exprListparentNTS() throws GrammerException {
        if(currentTerminal == Terminals.LEFTRBRACKET || currentTerminal == Terminals.ADDOPR || currentTerminal == Terminals.NOT || currentTerminal == Terminals.IDENT || currentTerminal == Terminals.LITERAL){
            expr();
            exprListNTS();
        }
    }

    private void exprListNTS()throws GrammerException {
        if(currentTerminal == Terminals.COMMA){
            consume(Terminals.COMMA);
            expr();
            exprListNTS();
        }
    }


    private void paramList() throws GrammerException{
        consume(Terminals.LEFTRBRACKET);
        paramListNTS();
        consume(Terminals.RIGHTRBRACKET);
    }

    private void paramListNTS() throws GrammerException{
        if(currentTerminal == Terminals.IDENT || currentTerminal == Terminals.MECHMODE || currentTerminal == Terminals.CHANGEMODE){
            param();
            paramNTS();
        }
    }

    private void param() throws GrammerException{
        if(currentTerminal == Terminals.IDENT || currentTerminal == Terminals.MECHMODE || currentTerminal == Terminals.CHANGEMODE){
            changeModeNTS();
            mechModeNTS();
            typeident();
        }
    }

    private void changeModeNTS() throws GrammerException {
        if(currentTerminal == Terminals.CHANGEMODE){
            consume(Terminals.CHANGEMODE);
        }
    }

    private void mechModeNTS() throws GrammerException {
        if(currentTerminal == Terminals.MECHMODE){
            consume(Terminals.MECHMODE);
        }
    }

    private void paramNTS() throws GrammerException {
        if(currentTerminal == Terminals.COMMA){
            consume(Terminals.COMMA);
            param();
            paramNTS();
        }
    }

    private void typeident() throws GrammerException{
        consume(Terminals.IDENT);
        consume(Terminals.COLON);
        consume(Terminals.TYPE);
    }







    //parse


}
