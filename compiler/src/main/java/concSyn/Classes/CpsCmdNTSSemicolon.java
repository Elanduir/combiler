package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class CpsCmdNTSSemicolon implements ICpsCmdNTS {

    private final Base T_semicolon;
    private final ICmd N_cmd;
    private final ICpsCmdNTS N_cpsCmdNTS;

    public CpsCmdNTSSemicolon(Base t_semicolon, ICmd n_cmd, ICpsCmdNTS n_cpsCmdNTS) {
        T_semicolon = t_semicolon;
        N_cmd = n_cmd;
        N_cpsCmdNTS = n_cpsCmdNTS;
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
