package Scanner;

public class Literal extends Base{
    private final int value;
    public Literal(int value) {
        super(Terminals.LITERAL);
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public String toString(){
        return "(" + super.toString() + ", " + this.value + ")";
    }
}
