package Scanner;

public class Ident extends Base{
    private final String value;
    public Ident(String value) {
        super(Terminals.IDENT);
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public String toString(){
        return "(" + super.toString() + ", \"" + this.value + "\")";
    }
}