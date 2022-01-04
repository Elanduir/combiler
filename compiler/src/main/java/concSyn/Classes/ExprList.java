package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class ExprList implements IExprList{

    private final Base T_leftbracket;
    private final IExprListParenNTS N_exprListParentNTS;
    private final Base T_rightbracket;

    public ExprList(Base t_leftbracket, IExprListParenNTS n_exprListParentNTS, Base t_rightbracket) {
        T_leftbracket = t_leftbracket;
        N_exprListParentNTS = n_exprListParentNTS;
        T_rightbracket = t_rightbracket;
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
