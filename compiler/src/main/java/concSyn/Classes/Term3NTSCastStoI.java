package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class Term3NTSCastStoI implements ITerm3NTS{

    private final Base T_castStoI;
    private final ITerm4 N_term4;
    private final ITerm3NTS N_term3NTS;

    public Term3NTSCastStoI(Base t_castStoI, ITerm4 n_term4, ITerm3NTS n_term3NTS) {
        T_castStoI = t_castStoI;
        N_term4 = n_term4;
        N_term3NTS = n_term3NTS;
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
