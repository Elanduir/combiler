package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class Term3 implements ITerm3{
    private final ITerm4 N_Term4;
    private final ITerm3NTS N_Term3NTS;

    public Term3(ITerm4 n_Term4, ITerm3NTS n_Term3NTS) {
        N_Term4 = n_Term4;
        N_Term3NTS = n_Term3NTS;
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

