package Scanner;

public class Base{
    public Terminals terminal;

    public Base(Terminals terminal){
        this.terminal = terminal;
    }

    public Terminals getTerminal(){
        return this.terminal;
    }

    @Override
    public String toString(){
        return this.terminal.toString();
    }
}
