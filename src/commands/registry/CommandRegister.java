/**
 * Абстрактный класс, читающий и исполняющий команды
 */
package commands.registry;
import commands.*;

import java.util.HashMap;
import java.util.Scanner;

public abstract class CommandRegister {
    Scanner scanner;
    String command;
    HashMap<String, NoArgCommand> noArgCommandMap = new HashMap<>();
    HashMap<String, ArgCommand> argCommandMap = new HashMap<>();
    final static int COMMAND_INDEX = 2;
    public CommandRegister(){
        noArgCommandMap.put("help", new HelpCommand());
        noArgCommandMap.put("info", new InfoCommand());
        noArgCommandMap.put("show", new ShowCommand());
        noArgCommandMap.put("clear", new ClearCommand());
        noArgCommandMap.put("save", new SaveCommand());
        noArgCommandMap.put("exit", new ExitCommand());
        noArgCommandMap.put("head", new HeadCommand());
        noArgCommandMap.put("sum_of_price", new SumOfPriceCommand());
        noArgCommandMap.put("print_descending", new PrintDescendingCommand());

        argCommandMap.put("add", new AddCommand());
        argCommandMap.put("update_id", new UpdateIdCommand());
        argCommandMap.put("remove_by_id", new RemoveByIdCommand());
        argCommandMap.put("execute_script", new ExecuteScriptCommand());
        argCommandMap.put("add_if_min", new AddIfMinCommand());
        argCommandMap.put("remove_lower", new RemoveLowerCommand());
        argCommandMap.put("filter_greater_than_price", new FGTPCommand());
    }
    public void printCommandNotFound(){
        System.out.println("Команда не опознана. Введите help, чтобы вывести справку по доступным командам");
    }
    public void run() {
        while (this.scanner.hasNextLine()) {
            command = this.scanner.nextLine().trim();
                String[] comSplit = command.split("\\s+", COMMAND_INDEX);
                String method = comSplit[0];
                if (noArgCommandMap.containsKey(method)) {
                    noArgCommandMap.get(method).execute();
                }
                else if(argCommandMap.containsKey(method)) {
                    String argument = comSplit[1].strip();
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
