package Scanner;

public class Type extends Base{
    public Types value;
    public Type(String type) {
        super(Terminals.TYPE);
        switch (type){
            case "short":
                this.value = Types.SHORT;
                break;
            case "int":
                this.value = Types.INT;
                break;
            case "long":
                this.value = Types.LONG;
                break;
        }
    }

    public Types getValue(){
        return this.value;
    }

    public String toString(){
        return "(" + super.toString() + ", " + this.value + ")";
    }


}
