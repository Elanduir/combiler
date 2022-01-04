package concSyn.Classes;

import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class StoDeclChangeMode implements IStoDecl{

    private final ITypedIdent N_TypedIdent;
    private final Base T_changemode;

    public StoDeclChangeMode(Base T_changemode, ITypedIdent N_TypedIdent){
        this.N_TypedIdent = N_TypedIdent;
        this.T_changemode = T_changemode;
    }


    @Override
    public String toString(String indent) {
        String subindent = indent + " ";
        String s = "";
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                if(field.getType() == Base.class) {
                    s += indent + field.get(this) + "\n";
                } else if (field.get(this) instanceof IProduction) {
                    s += ((IProduction)field.get(this)).toString(subindent);
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return s;
    }
}
