package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class ExprListNTS implements IExprListNTS{

    private final Base T_comma;
    private final IExpr N_expr;
    private final IExprListNTS N_exprListNTS;

    public ExprListNTS(Base t_comma, IExpr n_expr, IExprListNTS n_exprListNTS) {
        T_comma = t_comma;
        N_expr = n_expr;
        N_exprListNTS = n_exprListNTS;
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
