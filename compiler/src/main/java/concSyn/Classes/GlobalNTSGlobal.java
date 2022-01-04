package concSyn.Classes;

import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class GlobalNTSGlobal implements IGlobalNTS{

    private final Base T_global;
    private final ICpsDecl N_cpsDecl;

    public GlobalNTSGlobal(Base T_global, ICpsDecl N_cpsDecl){
        this.T_global = T_global;
        this.N_cpsDecl = N_cpsDecl;
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
