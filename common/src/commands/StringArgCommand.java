package commands;

import products.Product;

public class StringArgCommand extends Command{
    String argument;
    public StringArgCommand(CommandType type, String arg){
        super(type);
        this.argument = arg;
    }
    StringArgCommand(){
    }
    public void setArgument(String arg){
        this.argument = arg;
    }
    public String getArgument(){
        return this.argument;
    }
}
