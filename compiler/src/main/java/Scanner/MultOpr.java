package Scanner;

public class MultOpr extends Base{
    private final Operators value;
    public MultOpr(Operators value) {
        super(Terminals.MULTOPR);
        this.value = value;
    }

    public Operators getValue(){
        return this.value;
    }

    public String toString(){
        return "(" + super.toString() + ", " + this.value + ")";
    }
}
