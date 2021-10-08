public class RelOpr extends Base{
    private final Operators value;
    public RelOpr(Operators value) {
        super(Terminals.RELOPR);
        this.value = value;
    }

    public Operators getValue(){
        return this.value;
    }

    public String toString(){
        return "(" + super.toString() + "," + this.value + ")";
    }
}
