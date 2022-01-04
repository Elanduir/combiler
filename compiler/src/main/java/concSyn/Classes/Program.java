package concSyn.Classes;
import Scanner.Base;
import concSyn.Interfaces.*;

import java.lang.reflect.Field;


public class Program implements IProgram{
    final Base T_program;
    final Base T_ident;
    final IGlobalNTS N_globalNTS;
    final Base T_do;
    final ICpsCmd N_cpsCmd;
    final Base T_endprogram;

    public Program(
            final Base program,
            final Base T_ident,
            final IGlobalNTS N_globalNTS,
            final Base T_do,
            final ICpsCmd N_cpsCmd,
            final Base T_endprogram ) {

        this.T_program = program;
        this.T_ident = T_ident;
        this.N_globalNTS = N_globalNTS;
        this.T_do = T_do;
        this.N_cpsCmd = N_cpsCmd;
        this.T_endprogram = T_endprogram;
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
                System.out.println(field.getType());
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return s;

    }

    @Override
    public absSyn.Program toAbstractSyntax() {
        return new absSyn.Program();
    }
}
