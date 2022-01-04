package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class Term6NTSCastItoL implements ITerm6NTS{

    private final Base T_castItoL;
    private final ITerm7 N_term7;
    private final ITerm6NTS N_term6NTS;

    public Term6NTSCastItoL(Base t_castItoL, ITerm7 n_term7, ITerm6NTS n_term6NTS) {
        T_castItoL = t_castItoL;
        N_term7 = n_term7;
        N_term6NTS = n_term6NTS;
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
