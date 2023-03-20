package commands;

import com.google.gson.JsonSyntaxException;

public class AddCommand extends NewElementCommand implements ArgCommand{
    private final CommandExecuter comEx;
    public AddCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    @Override
    public void execute(String arg) {
        try {
            setJsonProduct(arg);
            comEx.add(newProduct());
        } catch (JsonSyntaxException e) {
            System.out.println("Ошибка в описании продукта в формате json");
        }
    }
}
