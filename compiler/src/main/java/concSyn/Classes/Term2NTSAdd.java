package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class Term2NTSAdd implements ITerm2NTS{

    private final Base T_addOpr;
    private final ITerm3 N_term3;
    private final ITerm2NTS N_term2NTS;

    public Term2NTSAdd(Base t_addOpr, ITerm3 n_term3, ITerm2NTS n_term2NTS) {
        T_addOpr = t_addOpr;
        N_term3 = n_term3;
        N_term2NTS = n_term2NTS;
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
