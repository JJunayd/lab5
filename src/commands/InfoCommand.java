package commands;


public class InfoCommand implements Executable{
    private final CommandExecuter comEx;
    public InfoCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    @Override
    public void execute() {
        comEx.info();
    }
}

