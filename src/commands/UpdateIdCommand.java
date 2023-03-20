package commands;

import com.google.gson.JsonSyntaxException;

public class UpdateIdCommand extends NewElementCommand implements ArgCommand{
    private final CommandExecuter comEx;
    public UpdateIdCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    @Override
    public void execute(String arg) {
        try {
            setJsonProduct(arg);
            comEx.updateId(newProduct());
        } catch (JsonSyntaxException e) {
            System.out.println("Ошибка в описании продукта в формате json");
        }
    }
}
