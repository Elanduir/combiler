package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class ExprListparentNTS implements  IExprListParenNTS {

    private final IExpr N_expr;
    private final IExprListNTS N_exprListNTS;

    public ExprListparentNTS(IExpr n_expr, IExprListNTS n_exprListNTS) {
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
