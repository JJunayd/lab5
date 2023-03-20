package commands;

import com.google.gson.JsonSyntaxException;

public class AddIfMinCommand extends NewElementCommand implements ArgCommand{
    private final CommandExecuter comEx;
    public AddIfMinCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    public void execute(String arg) {
        try {
            setJsonProduct(arg);
            comEx.addIfMin(newProduct());
        } catch (JsonSyntaxException e) {
            System.out.println("Ошибка в описании продукта в формате json");
        }
    }
}
