package concSyn.Classes;

import Scanner.Ident;
import Scanner.Terminals;
import Scanner.Type;
import concSyn.Interfaces.*;

public class TypeIdent implements  ITypedIdent{

    private final Ident ident;
    private final Terminals colon = Terminals.COLON;
    private final Type type;

    public TypeIdent(Ident ident, Type type){
        this.ident = ident;
        this.type =  type;
    }


    @Override
    public String toString(String indent) {
        return indent + ident + colon + type;
    }
}
