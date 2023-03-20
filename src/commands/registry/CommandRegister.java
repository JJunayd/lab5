package commands.registry;
import commands.*;

import java.util.HashMap;
import java.util.Scanner;

public abstract class CommandRegister {
    Scanner scanner;
    String command;
    CommandExecuter comEx;
    HashMap<String, Executable> noArgCommandMap = new HashMap<>();
    HashMap<String, ArgCommand> argCommandMap = new HashMap<>();
    final static int COMMAND_INDEX = 2;
    public CommandRegister(CommandExecuter comEx){
        this.comEx = comEx;
        var add = new AddCommand(comEx);
        var addIfMin = new AddIfMinCommand(comEx);
        var clear = new ClearCommand(comEx);
        var exScript = new ExecuteScriptCommand(comEx);
        var exit = new ExitCommand(comEx);
        var filterPrice = new FGTPCommand(comEx);
        var head = new HeadCommand(comEx);
        var help = new HelpCommand(comEx);
        var info = new InfoCommand(comEx);
        var printDescending = new PrintDescendingCommand(comEx);
        var removeById = new RemoveByIdCommand(comEx);
        var removeLower = new RemoveLowerCommand(comEx);
        var save = new SaveCommand(comEx);
        var show = new ShowCommand(comEx);
        var sumOfPrice = new SumOfPriceCommand(comEx);
        var updateId = new UpdateIdCommand(comEx);
        noArgCommandMap.put("help", help);
        noArgCommandMap.put("info", info);
        noArgCommandMap.put("show", show);
        noArgCommandMap.put("clear", clear);
        noArgCommandMap.put("save", save);
        noArgCommandMap.put("exit", exit);
        noArgCommandMap.put("head", head);
        noArgCommandMap.put("sum_of_price", sumOfPrice);
        noArgCommandMap.put("print_descending", printDescending);

        argCommandMap.put("add", add);
        argCommandMap.put("update_id", updateId);
        argCommandMap.put("remove_by_id", removeById);
        argCommandMap.put("execute_script", exScript);
        argCommandMap.put("add_if_min", addIfMin);
        argCommandMap.put("remove_lower", removeLower);
        argCommandMap.put("filter_greater_than_price", filterPrice);
    }
    public void printCommandNotFound(){
        System.out.println("Команда не опознана. Введите help, чтобы вывести справку по доступным командам");
    }
    public void run() {
        while (this.scanner.hasNextLine()) {
            command = this.scanner.nextLine().trim();
                String[] comSplit = command.split("\\s+", COMMAND_INDEX);
                String method = comSplit[0];
                String argument = comSplit[1].strip();
                if (noArgCommandMap.containsKey(method)) {
                    noArgCommandMap.get(method).execute();
                }
                else if(argCommandMap.containsKey(method)) {
                    argCommandMap.get(method).execute(argument);
                }
                else {
                    printCommandNotFound();
                    }
                }
            }
        public void setScanner(Scanner scanner){
            this.scanner = scanner;
        }
}
