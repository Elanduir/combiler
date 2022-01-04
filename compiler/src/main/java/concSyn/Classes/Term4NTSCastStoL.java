package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class Term4NTSCastStoL implements ITerm4NTS{

    private final Base T_castStoL;
    private final ITerm5 N_term5;
    private final ITerm4NTS N_term4NTS;

    public Term4NTSCastStoL(Base t_castStoL, ITerm5 n_term5, ITerm4NTS n_term4NTS) {
        T_castStoL = t_castStoL;
        N_term5 = n_term5;
        N_term4NTS = n_term4NTS;
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
