package Scanner;

public class Mechmode extends Base{
    public Modes value;
    public Mechmode(String value){
        super(Terminals.MECHMODE);
        switch(value){
            case "copy":
                this.value = Modes.COPY;
                break;
            case "ref":
                this.value = Modes.REF;
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

