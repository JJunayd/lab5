import commands.CommandExecuter;
import commands.registry.InputCommandRegister;
import parser.Parser;

public class Main {
    public static void main(String[] args) {
        Parser.loadProducts();
        CommandExecuter comEx = new CommandExecuter();
        InputCommandRegister inputCommandRegister = new InputCommandRegister(comEx);
        inputCommandRegister.run();
    }
}