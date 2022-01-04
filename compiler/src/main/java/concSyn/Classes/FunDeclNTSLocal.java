package concSyn.Classes;

import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class FunDeclNTSLocal implements IFunDeclNTS{

    private final Base T_local;
    private final ICpsStoDecl N_cpsStoDecl;

    public FunDeclNTSLocal(Base T_local, ICpsStoDecl N_cpsStoDecl){
        this.T_local = T_local;
        this.N_cpsStoDecl = N_cpsStoDecl;

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
