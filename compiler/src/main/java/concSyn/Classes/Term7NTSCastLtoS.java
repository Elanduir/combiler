package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class Term7NTSCastLtoS implements ITerm7NTS {

    private final Base T_castLtoS;
    private final ITerm8 N_term8;
    private final ITerm7NTS N_term7NTS;

    public Term7NTSCastLtoS(Base t_castLtoS, ITerm8 n_term8, ITerm7NTS n_term7NTS) {
        T_castLtoS = t_castLtoS;
        N_term8 = n_term8;
        N_term7NTS = n_term7NTS;
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
