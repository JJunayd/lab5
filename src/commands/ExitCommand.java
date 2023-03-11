package commands;

public class ExitCommand implements Executable{
    private final CommandExecuter comEx;
    public ExitCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }

    @Override
    public void execute() {
        comEx.exit();
    }
}
