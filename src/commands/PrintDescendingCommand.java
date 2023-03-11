package commands;


public class PrintDescendingCommand implements Executable{
    private final CommandExecuter comEx;
    public PrintDescendingCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    @Override
    public void execute() {
        comEx.printDescending();
    }
}