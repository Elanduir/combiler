package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class CpsStoDeclNTSSemicolon implements ICpsStoDeclNTS{

    private final Base T_semicolon;
    private final IStoDecl N_stoDecl;
    private final ICpsStoDeclNTS N_cpsStoDeclNTS;

    public CpsStoDeclNTSSemicolon(Base T_semicolon, IStoDecl N_stoDecl, ICpsStoDeclNTS N_cpsStoDeclNTS){
        this.T_semicolon = T_semicolon;
        this.N_stoDecl = N_stoDecl;
        this.N_cpsStoDeclNTS = N_cpsStoDeclNTS;
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
