package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class ProcDeclNTSLocal implements IProcDeclNTS{

    private final Base T_local;
    private final ICpsStoDecl N_cpsStoDecl;

    public ProcDeclNTSLocal(Base t_local, ICpsStoDecl n_cpsStoDecl) {
        T_local = t_local;
        N_cpsStoDecl = n_cpsStoDecl;
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
