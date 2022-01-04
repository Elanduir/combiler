package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class FactorMonadic implements IFactor{

    private final IMonadicOpr N_monadicOpr;
    private final IFactorNTS N_factorNTS;

    public FactorMonadic(IMonadicOpr n_monadicOpr, IFactorNTS n_factorNTS) {
        N_monadicOpr = n_monadicOpr;
        N_factorNTS = n_factorNTS;
    }

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
