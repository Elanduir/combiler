package concSyn.Classes;

import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class TypedIdent implements ITypedIdent{
    private final Base T_ident;
    private final Base T_colon;
    private final Base T_type;

    public TypedIdent(Base T_ident, Base T_colon, Base T_type){
        this.T_ident = T_ident;
        this.T_colon = T_colon;
        this.T_type = T_type;
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
