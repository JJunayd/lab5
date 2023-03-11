package commands;
public class SumOfPriceCommand implements Executable {
    private final CommandExecuter comEx;
    public SumOfPriceCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }

    @Override
    public void execute() {
        comEx.sumOfPrice();
    }
}
