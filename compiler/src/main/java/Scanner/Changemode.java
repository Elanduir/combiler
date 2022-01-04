package Scanner;

public class Changemode extends Base{
    public Modes value;
    public Changemode(String value){
        super(Terminals.CHANGEMODE);
        switch(value){
            case "var":
                this.value = Modes.VAR;
                break;
            case "const":
                this.value = Modes.CONST;
                break;
        }
    }

    public Modes getValue(){
        return this.value;
    }

    public String toString(){
        return "(" + super.toString() + ", " + this.value + ")";
    }
}

