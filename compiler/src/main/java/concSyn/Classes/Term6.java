package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class Term6 implements ITerm6{
    private final ITerm7 N_Term7;
    private final ITerm6NTS N_Term6NTS;

    public Term6(ITerm7 n_Term7, ITerm6NTS n_Term6NTS) {
        N_Term7 = n_Term7;
        N_Term6NTS = n_Term6NTS;
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

