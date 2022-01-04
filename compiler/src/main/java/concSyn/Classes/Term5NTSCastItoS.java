package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class Term5NTSCastItoS implements ITerm5NTS{

    private final Base T_castItoS;
    private final ITerm6 N_term6;
    private final ITerm5NTS N_termNTS5;

    public Term5NTSCastItoS(Base t_castItoS, ITerm6 n_term6, ITerm5NTS n_termNTS5) {
        T_castItoS = t_castItoS;
        N_term6 = n_term6;
        N_termNTS5 = n_termNTS5;
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
