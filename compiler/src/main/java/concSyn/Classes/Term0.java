package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class Term0 implements ITerm0{
    private final ITerm1 N_term1;
    private final ITerm0NTS N_term0NTS;

    public Term0(ITerm1 n_term1, ITerm0NTS n_term0NTS) {
        N_term1 = n_term1;
        N_term0NTS = n_term0NTS;
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
