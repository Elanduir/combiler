package concSyn.Classes;

import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class CpsDeclNTS implements ICpsDeclNTS{
    private final Base T_semicolon;
    private final IDecl N_decl;
    private final ICpsDeclNTS N_cpsDeclNTS;
    public CpsDeclNTS(Base T_semicolon, IDecl N_decl, ICpsDeclNTS N_cpsDeclNTS){
        this.T_semicolon = T_semicolon;
        this.N_decl = N_decl;
        this.N_cpsDeclNTS = N_cpsDeclNTS;
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
