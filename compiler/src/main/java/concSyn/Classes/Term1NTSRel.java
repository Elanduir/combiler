package concSyn.Classes;

import Scanner.Base;
import concSyn.Interfaces.IProduction;

import java.lang.reflect.Field;

import concSyn.Interfaces.*;

public class Term1NTSRel implements ITerm1NTS{

    private final Base T_relOpr;
    private final ITerm2 N_term2;


    public Term1NTSRel(Base t_relOpr, ITerm2 n_term2) {
        T_relOpr = t_relOpr;
        N_term2 = n_term2;

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
