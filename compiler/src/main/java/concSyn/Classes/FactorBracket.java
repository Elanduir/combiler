package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class FactorBracket implements IFactor{

    private final Base T_leftbracket;
    private final IExpr N_expr;
    private final Base T_rightbracket;

    public FactorBracket(Base t_leftbracket, IExpr n_expr, Base t_rightbracket) {
        T_leftbracket = t_leftbracket;
        N_expr = n_expr;
        T_rightbracket = t_rightbracket;
    }

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
