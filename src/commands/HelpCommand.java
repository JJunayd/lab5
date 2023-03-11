package commands;

public class HelpCommand implements Executable{
    private final CommandExecuter comEx;
    public HelpCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    @Override
    public void execute() {
        comEx.help();
    }
}
