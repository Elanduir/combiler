package concSyn.Classes;

import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class CmdIf implements ICmd{

    private final Base T_if;
    private final IExpr N_expr;
    private final Base T_then;
    private final ICpsCmd N_cpsCmd;
    private final IIfElseNTS N_ifElseNTS;
    private final Base T_endif;

    public CmdIf(Base t_if, IExpr n_expr, Base t_then, ICpsCmd n_cpsCmd, IIfElseNTS n_ifElseNTS, Base t_endif) {
        T_if = t_if;
        N_expr = n_expr;
        T_then = t_then;
        N_cpsCmd = n_cpsCmd;
        N_ifElseNTS = n_ifElseNTS;
        T_endif = t_endif;
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
