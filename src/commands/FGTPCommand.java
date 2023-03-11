package commands;


public class FGTPCommand implements Executable {
    private final CommandExecuter comEx;
    public FGTPCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    private long price;
    @Override
    public void execute() {
        comEx.filterGreaterThanPrice(price);
    }


    public void setPrice(long price) {
        this.price = price;
    }
}
