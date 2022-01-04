package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class ParamList implements IParamList{

    private final Base T_leftbracket;
    private final IParamListNTS N_paramListNTS;
    private final Base T_rightbracket;

    public ParamList(Base t_leftbracket, IParamListNTS n_paramListNTS, Base t_rightbracket) {
        T_leftbracket = t_leftbracket;
        N_paramListNTS = n_paramListNTS;
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
