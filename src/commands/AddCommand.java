package commands;

public class AddCommand extends NewElementCommand implements Executable {
    private final CommandExecuter comEx;
    public AddCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    @Override
    public void execute() {
        comEx.add(newProduct());
    }
}
