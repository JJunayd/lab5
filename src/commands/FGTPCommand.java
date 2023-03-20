package commands;


public class FGTPCommand implements ArgCommand {
    private final CommandExecuter comEx;
    public FGTPCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    @Override
    public void execute(String arg) {
        try {
            long price = Long.parseLong(arg);
            comEx.filterGreaterThanPrice(price);
        } catch (NumberFormatException e) {
            System.out.println("Значение price должно быть числом, не превышающим 9,223,372,036,854,775,807");
        }
    }
}
