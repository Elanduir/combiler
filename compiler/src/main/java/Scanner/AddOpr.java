package Scanner;

public class AddOpr extends Base{
    private final Operators value;
    public AddOpr(Operators value) {
        super(Terminals.ADDOPR);
        this.value = value;
    }

    public Operators getValue(){
        return this.value;
    }

    public String toString(){
        return "(" + super.toString() + "," + this.value + ")";
    }
}
