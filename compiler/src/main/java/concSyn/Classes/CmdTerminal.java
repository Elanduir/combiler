package concSyn.Classes;

import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class CmdTerminal implements ICmd{

    private final IExpr N_expr1;
    private final Base T_becomes;
    private final IExpr N_expr2;

    public CmdTerminal(IExpr n_expr1, Base t_becomes, IExpr n_expr2) {
        N_expr1 = n_expr1;
        T_becomes = t_becomes;
        N_expr2 = n_expr2;
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
