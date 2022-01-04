package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class ProcDecl implements IProcDecl{

    public ProcDecl(Base t_procedur, Base t_ident, IParamList n_paramList, IProcDeclNTS n_procDeclNTS, Base t_do, ICpsCmd n_cpsCmd, Base t_endprocedur) {
        T_procedur = t_procedur;
        T_ident = t_ident;
        N_paramList = n_paramList;
        N_procDeclNTS = n_procDeclNTS;
        T_do = t_do;
        N_cpsCmd = n_cpsCmd;
        T_endprocedur = t_endprocedur;
    }

    private final Base T_procedur;
    private final Base T_ident;
    private final IParamList N_paramList;
    private final IProcDeclNTS N_procDeclNTS;
    private final Base T_do;
    private final ICpsCmd N_cpsCmd;
    private final Base T_endprocedur;




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
