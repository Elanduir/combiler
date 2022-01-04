package Scanner;

public class Flowmode extends Base{
    public Modes value;
    public Flowmode(String value){
        super(Terminals.FLOWMODE);
        switch(value){
            case "in":
                this.value = Modes.IN;
                break;
            case "out":
                this.value = Modes.OUT;
                break;
            case "inout":
                this.value = Modes.INOUT;
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
