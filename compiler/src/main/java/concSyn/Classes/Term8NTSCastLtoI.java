package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class Term8NTSCastLtoI implements ITerm8NTS{

    private final Base T_castLtoI;
    private final ITerm9 N_term9;
    private final ITerm8NTS N_term8NTS;

    public Term8NTSCastLtoI(Base t_castLtoI, ITerm9 n_term9, ITerm8NTS n_term8NTS) {
        T_castLtoI = t_castLtoI;
        N_term9 = n_term9;
        N_term8NTS = n_term8NTS;
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
