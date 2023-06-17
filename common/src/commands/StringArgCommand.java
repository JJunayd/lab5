package commands;


public class StringArgCommand extends Command{
    String argument;
    String addArgument;
    public StringArgCommand(CommandType type, String arg){
        super(type);
        this.argument = arg;
    }
    public StringArgCommand(CommandType type, String arg, String addArg){
        super(type);
        this.argument = arg;
        this.addArgument = addArg;
    }
    StringArgCommand(){
    }
    public void setAddArgument(String addArg){
        this.addArgument = addArg;
    }
    public String getAddArgument(){
        return this.addArgument;
    }
    public void setArgument(String arg){
        this.argument = arg;
    }
    public String getArgument(){
        return this.argument;
    }
}
