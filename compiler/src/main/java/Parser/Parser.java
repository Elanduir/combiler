package Parser;

import Scanner.Base;
import Scanner.Terminals;
import java.util.List;

import concSyn.Classes.*;
import concSyn.Interfaces.*;

//TODO: 76 & 160

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

    private IProgram program() throws GrammerException {
        Base T_prog = consume(Terminals.PROGRAM);
        Base T_ident = consume(Terminals.IDENT);
        IGlobalNTS N_globalNTS = globalNTS();
        Base T_do = consume(Terminals.DO);
        ICpsCmd N_cpsCmd = cpsCmd();
        Base T_endprogram = consume(Terminals.ENDPROGRAM);

        return new Program(T_prog, T_ident, N_globalNTS, T_do, N_cpsCmd, T_endprogram);

    }

    private IGlobalNTS globalNTS() throws GrammerException{
        if(currentTerminal == Terminals.GLOBAL){
            Base T_global = consume(Terminals.GLOBAL);
            ICpsDecl N_cpsDecl = cpsDecl();
            return new GlobalNTSGlobal(T_global, N_cpsDecl);
        }else if(currentTerminal == Terminals.DO){
            return new GlobalNTSEpsilon();
        }
        throw new GrammerException(Terminals.GLOBAL.toString());
    }

    private ICpsDecl cpsDecl() throws GrammerException{
        if(currentTerminal == Terminals.FUNCTION || currentTerminal == Terminals.IDENT || currentTerminal == Terminals.PROCEDUR || currentTerminal == Terminals.CHANGEMODE){
            IDecl N_decl =  decl();
            ICpsDeclNTS N_cpsDeclNTS = cpsDeclNTS();
            return new CpsDcl(N_decl, N_cpsDeclNTS);
        }else{
            throw new GrammerException("Invalid token at: " + currentToken);
        }

    }

    private IDecl decl() throws  GrammerException{
        if( currentTerminal == Terminals.IDENT ||  currentTerminal == Terminals.CHANGEMODE){
            IStoDecl N_stoDecl = stoDecl();
            return new DeclSto(N_stoDecl);
        }else if(currentTerminal == Terminals.FUNCTION){
            IFunDecl N_funDecl = funDecl();
            return new DeclFun(N_funDecl);
        }else if(currentTerminal == Terminals.PROCEDUR){
            IProcDecl N_procDecl = procDecl();
            return new DeclProc(N_procDecl);
        }else{
            throw new GrammerException("Invalid token at: " + currentToken);
        }
    }

    private ICpsDeclNTS cpsDeclNTS() throws  GrammerException{
        if(currentTerminal == Terminals.SEMICOLON){
            Base T_semicolon = consume(Terminals.SEMICOLON);
            IDecl N_decl = decl();
            ICpsDeclNTS N_cpsDeclNTS = cpsDeclNTS();
            return new CpsDeclNTS(T_semicolon, N_decl, N_cpsDeclNTS);
        }else{
            throw new GrammerException("Invalid token at: " + currentToken);
        }
    }

    private IStoDecl stoDecl() throws GrammerException{
        if(currentTerminal == Terminals.CHANGEMODE){
            Base T_changemode = consume(Terminals.CHANGEMODE);
            ITypedIdent N_TypeIdent = typeident();
            return new StoDeclChangeMode(T_changemode, N_TypeIdent);
        }else{
            ITypedIdent N_typeIdent = typeident();
            return new StoDeclTypeIdent(N_typeIdent);
        }
    }

    private IFunDecl funDecl() throws GrammerException{
        return new FunDecl(consume(Terminals.FUNCTION),
        consume(Terminals.IDENT),
        paramList(),
        consume(Terminals.RETURNS),
        stoDecl(),
        funDeclNTS(),
        consume(Terminals.DO),
        cpsCmd(),
        consume(Terminals.ENDFUN));
    }

    private IFunDeclNTS funDeclNTS() throws GrammerException{
        if(currentTerminal == Terminals.LOCAL){
            return new FunDeclNTSLocal(
            consume(Terminals.LOCAL),
            cpsStoDecl());
        }else{
            return new FunDeclNTSEpsilon();
        }
    }

    private ICpsStoDecl cpsStoDecl() throws GrammerException{
        return new CpsStoDecl(
        stoDecl(),
        cpsStoDeclNTS());
    }

    private ICpsStoDeclNTS cpsStoDeclNTS() throws  GrammerException {
        if(currentTerminal == Terminals.SEMICOLON){
            return new CpsStoDeclNTSSemicolon(
            consume(Terminals.SEMICOLON),
            stoDecl(),
            cpsStoDeclNTS());
        }else{
            return new CpsStoDeclNTSEpsilon();
        }
    }

    private IProcDecl procDecl() throws GrammerException{
        return new ProcDecl(
        consume(Terminals.PROCEDUR),
        consume(Terminals.IDENT),
        paramList(),
        procDeclNTS(),
        consume(Terminals.DO),
        cpsCmd(),
        consume(Terminals.ENDPROCEDUR));
    }

    private IProcDeclNTS procDeclNTS() throws GrammerException {
        if(currentTerminal == Terminals.LOCAL){
            return new ProcDeclNTSLocal(
            consume(Terminals.LOCAL),
            cpsStoDecl());
        }else{
            return new ProcDeclNTSEpsilon();
        }
    }

    private ICpsCmd cpsCmd() throws  GrammerException{
        if(currentTerminal == Terminals.DEBUGOUT || currentTerminal == Terminals.DEBUGIN || currentTerminal == Terminals.CALL || currentTerminal == Terminals.WHILE || currentTerminal == Terminals.IF || currentTerminal == Terminals.LEFTRBRACKET || currentTerminal == Terminals.ADDOPR || currentTerminal == Terminals.NOT || currentTerminal == Terminals.IDENT || currentTerminal == Terminals.LITERAL || currentTerminal == Terminals.SKIP){
            return new CpsCmdTerminal(
            cmd(),
            cpsCmdNTS());
        }else{
            return new CpsCmdEpsilon();
        }
    }

    private ICpsCmdNTS cpsCmdNTS() throws GrammerException {
        if(currentTerminal == Terminals.SEMICOLON){
            return new CpsCmdNTSSemicolon(
            consume(Terminals.SEMICOLON),
            cmd(),
            cpsCmdNTS());
        }else{
            return new CpsCmdNTSEpsilon();
        }
    }

    private ICmd cmd() throws GrammerException {
        if(currentTerminal == Terminals.LEFTRBRACKET || currentTerminal == Terminals.ADDOPR || currentTerminal == Terminals.NOT || currentTerminal == Terminals.IDENT || currentTerminal == Terminals.LITERAL){
            return new CmdTerminal(
            expr(),
            consume(Terminals.BECOMES),
            expr());
        }else if(currentTerminal == Terminals.SKIP){
            return new CmdSkip(consume(Terminals.SKIP));

        }else if(currentTerminal == Terminals.IF){
            return new CmdIf(
            consume(Terminals.IF),
            expr(),
            consume(Terminals.THEN),
            cpsCmd(),
            ifelseNTS(),
            consume(Terminals.ENDIF));
        }else if(currentTerminal == Terminals.WHILE){
            return new CmdWhile(
            consume(Terminals.WHILE),
            expr(),
            consume(Terminals.DO),
            cpsCmd(),
            consume(Terminals.ENDWHILE));
        }else if(currentTerminal == Terminals.DEBUGIN){
            return new CmdDebugIn(
            consume(Terminals.DEBUGIN),
            expr());
        }else if(currentTerminal == Terminals.DEBUGOUT){
            return new CmdDebugOut(
            consume(Terminals.DEBUGOUT),
            expr());
        }else if(currentTerminal == Terminals.CALL){
            return new CmdCall(
            consume(Terminals.CALL),
            consume(Terminals.IDENT),
            exprList());
        }else{
            throw new GrammerException("Invalid token at: " + currentToken);
        }
    }

    private IIfElseNTS ifelseNTS() throws GrammerException{
        if(currentTerminal == Terminals.ELSE){
            return new IfElseNTSElse(
            consume(Terminals.ELSE),
            cpsCmd());
        }else{
            return new IfElseNTSEpsilon();
        }
    }

    private IExpr expr() throws GrammerException {
        return new Expr(term0());
    }

    //region terms for everything
    public ITerm0 term0() throws  GrammerException {
        return new Term0(
        term1(),
        term0NTS());
    }

    public void term0NTS() throws  GrammerException {
        if(currentTerminal == Terminals.BOOLOPR){
            consume(Terminals.BOOLOPR);
            term1();
            term0NTS();
        }
    }

    public ITerm1 term1() throws  GrammerException {
        return new Term1(
        term2(),
        term1NTS());
    }

    public void term1NTS() throws  GrammerException {
        if(currentTerminal == Terminals.RELOPR){
            consume(Terminals.RELOPR);
            term2();
        }
    }

    public ITerm2 term2() throws  GrammerException {
        return new Term2(
        term3(),
        term2NTS());
    }

    public void term2NTS() throws  GrammerException {
        if(currentTerminal == Terminals.ADDOPR){
            consume(Terminals.ADDOPR);
            term3();
            term2NTS();
        }
    }

    public ITerm3 term3() throws  GrammerException {
        return new Term3(
        term4(),
        term3NTS());
    }

    public void term3NTS() throws  GrammerException {
        if(currentTerminal == Terminals.CASTSTOI){
            consume(Terminals.CASTSTOI);
            term4();
            term3NTS();
        }
    }

    public ITerm4 term4() throws  GrammerException {
        return new Term4(
        term5(),
        term4NTS());
    }

    public void term4NTS() throws  GrammerException {
        if(currentTerminal == Terminals.CASTSTOL){
            consume(Terminals.CASTSTOL);
            term5();
            term4NTS();
        }
    }

    public ITerm5 term5() throws  GrammerException {
        return new Term5(
        term6(),
        term5NTS());
    }

    public void term5NTS() throws  GrammerException {
        if(currentTerminal == Terminals.CASTITOS){
            consume(Terminals.CASTITOS);
            term6();
            term5NTS();
        }
    }

    public ITerm6 term6() throws  GrammerException {
        return new Term6(
        term7(),
        term6NTS());
    }

    public void term6NTS() throws  GrammerException {
        if(currentTerminal == Terminals.CASTITOL){
            term7();
            term6NTS();
        }
    }

    public ITerm7 term7() throws  GrammerException {
        return new Term7(
        term8(),
        term7NTS());
    }

    public void term7NTS() throws  GrammerException {
        if(currentTerminal == Terminals.CASTLTOS){
            term8();
            term7NTS();
        }
    }

    public ITerm8 term8() throws  GrammerException {
        return new Term8(
        term9(),
        term8NTS());
    }

    public void term8NTS() throws  GrammerException {
        if(currentTerminal == Terminals.CASTLTOI){
            consume(Terminals.CASTLTOI);
            term9();
            term8NTS();
        }
    }

    public ITerm9 term9() throws  GrammerException {
        return new Term9(
        factor(),
        term9NTS());
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

    private ITypedIdent typeident() throws GrammerException{
        Base T_Ident = consume(Terminals.IDENT);
        Base T_colon = consume(Terminals.COLON);
        Base T_type =consume(Terminals.TYPE);
        return new TypedIdent(T_Ident, T_colon, T_type);
    }


}
