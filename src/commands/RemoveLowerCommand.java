package commands;
import collection.Product;

public class RemoveLowerCommand extends NewElementCommand implements Executable{
    private final CommandExecuter comEx;
    public RemoveLowerCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    @Override
    public void execute() {
        Product product = fromJson(jsonProduct);
        comEx.removeLower(product);
    }
}
