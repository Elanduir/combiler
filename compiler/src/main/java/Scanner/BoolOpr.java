package Scanner;

public class BoolOpr extends Base{
    private Operators value;
    public BoolOpr(String value) {
        super(Terminals.BOOLOPR);
        switch (value){
            case "bAnd":
                this.value = Operators.BAND;
                break;
            case "bOr":
                this.value = Operators.BOR;
                break;
        }
    }

    public Operators getValue(){
        return this.value;
    }

    public String toString(){
        return "(" + super.toString() + ", " + this.value + ")";
    }
}
