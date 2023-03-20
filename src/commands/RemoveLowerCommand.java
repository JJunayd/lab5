package commands;
import collection.Product;
import com.google.gson.JsonSyntaxException;

public class RemoveLowerCommand extends NewElementCommand implements ArgCommand{
    private final CommandExecuter comEx;
    public RemoveLowerCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    @Override
    public void execute(String arg) {
        try {
            setJsonProduct(arg);
            Product product = fromJson(jsonProduct);
            comEx.removeLower(product);
        } catch (JsonSyntaxException e) {
            System.out.println("Ошибка в описании продукта в формате json");
        }
    }
}
