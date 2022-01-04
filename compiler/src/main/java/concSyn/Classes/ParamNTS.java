package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class ParamNTS implements IParamNTS{
    private final Base T_comma;
    private final IParam N_param;
    private final IParamNTS N_paramNTS;

    public ParamNTS(Base t_comma, IParam n_param, IParamNTS n_paramNTS) {
        T_comma = t_comma;
        N_param = n_param;
        N_paramNTS = n_paramNTS;
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
