package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class CmdWhile implements ICmd{

    private final Base T_while;
    private final IExpr N_expr;
    private final Base T_do;
    private final ICpsCmd N_cpsCmd;
    private final Base T_endWhile;

    public CmdWhile(Base t_while, IExpr n_expr, Base t_do, ICpsCmd n_cpsCmd, Base t_endWhile) {
        T_while = t_while;
        N_expr = n_expr;
        T_do = t_do;
        N_cpsCmd = n_cpsCmd;
        T_endWhile = t_endWhile;
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
