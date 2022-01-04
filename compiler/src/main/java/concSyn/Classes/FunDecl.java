package concSyn.Classes;

import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;

public class FunDecl implements IFunDecl{

    private final Base T_fun;
    private final Base T_ident;
    private final IParamList N_paramList;
    private final Base T_returns;
    private final IStoDecl N_stoDecl;
    private final IFunDeclNTS N_funDeclNTS;
    private final Base T_do;
    private final ICpsCmd N_cpsCmd;
    private final Base T_endfun;

    public FunDecl(Base T_fun, Base T_ident, IParamList N_paramList, Base T_returns, IStoDecl N_stoDecl, IFunDeclNTS N_funDeclNTS, Base T_do, ICpsCmd N_cpsCmd, Base T_endfun){
        this.T_fun = T_fun;
        this.T_ident = T_ident;
        this.N_paramList = N_paramList;
        this.T_returns = T_returns;
        this.N_stoDecl = N_stoDecl;
        this.N_funDeclNTS = N_funDeclNTS;
        this.T_do = T_do;
        this.N_cpsCmd = N_cpsCmd;
        this.T_endfun = T_endfun;
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
