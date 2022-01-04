package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class Term4 implements ITerm4{
    private final ITerm5 N_Term5;
    private final ITerm4NTS N_Term4NTS;

    public Term4(ITerm5 n_Term5, ITerm4NTS n_Term4NTS) {
        N_Term5 = n_Term5;
        N_Term4NTS = n_Term4NTS;
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

