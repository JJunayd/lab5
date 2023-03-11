package commands;

public class UpdateIdCommand extends NewElementCommand implements Executable{
    private final CommandExecuter comEx;
    public UpdateIdCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    @Override
    public void execute() {
        comEx.updateId(newProduct());
    }
}
