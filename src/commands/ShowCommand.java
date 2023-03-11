package commands;
public class ShowCommand implements Executable{
    private final CommandExecuter comEx;
    public ShowCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    @Override
    public void execute() {
        comEx.show();
    }
}
