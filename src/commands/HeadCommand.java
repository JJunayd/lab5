package commands;



public class HeadCommand implements Executable{
    private final CommandExecuter comEx;
    public HeadCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    @Override
    public void execute() {
        comEx.head();
    }
}
