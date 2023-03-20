package commands;
public class ClearCommand implements Executable {
    private final CommandExecuter comEx;
    public ClearCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    @Override
    public void execute() {
        comEx.clear();
    }
}
