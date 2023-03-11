package commands;

public class AddIfMinCommand extends NewElementCommand implements Executable{
    private final CommandExecuter comEx;
    public AddIfMinCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    public void execute() {
        comEx.addIfMin(newProduct());
    }
}
