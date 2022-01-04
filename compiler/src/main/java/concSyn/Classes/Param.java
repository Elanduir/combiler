package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class Param implements IParam{

    private final IChangeModeNTS N_changeModeNTS;
    private final IMechModeNTS N_mechModeNTS;
    private final ITypedIdent N_typeIdent;

    public Param(IChangeModeNTS n_changeModeNTS, IMechModeNTS n_mechModeNTS, ITypedIdent n_typeIdent) {
        N_changeModeNTS = n_changeModeNTS;
        N_mechModeNTS = n_mechModeNTS;
        N_typeIdent = n_typeIdent;
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
