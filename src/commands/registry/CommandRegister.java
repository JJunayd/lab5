package commands.registry;
import com.google.gson.JsonSyntaxException;
import commands.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class CommandRegister {
    Scanner scanner;
    String command;
    AddCommand add;
    AddIfMinCommand addIfMin;
    ClearCommand clear;
    ExecuteScriptCommand exScript;
    ExitCommand exit;
    FGTPCommand filterPrice;
    HeadCommand head;
    HelpCommand help;
    InfoCommand info;
    PrintDescendingCommand printDescending;
    RemoveByIdCommand removeById;
    RemoveLowerCommand removeLower;
    SaveCommand save;
    ShowCommand show;
    SumOfPriceCommand sumOfPrice;
    UpdateIdCommand updateId;
    CommandExecuter comEx;
    HashMap<String, Executable> commandMap = new HashMap<>();
    public CommandRegister(CommandExecuter comEx){
        this.comEx = comEx;
        add = new AddCommand(comEx);
        addIfMin = new AddIfMinCommand(comEx);
        clear = new ClearCommand(comEx);
        exScript = new ExecuteScriptCommand(comEx);
        exit = new ExitCommand(comEx);
        filterPrice = new FGTPCommand(comEx);
        head = new HeadCommand(comEx);
        help = new HelpCommand(comEx);
        info = new InfoCommand(comEx);
        printDescending = new PrintDescendingCommand(comEx);
        removeById = new RemoveByIdCommand(comEx);
        removeLower = new RemoveLowerCommand(comEx);
        save = new SaveCommand(comEx);
        show = new ShowCommand(comEx);
        sumOfPrice = new SumOfPriceCommand(comEx);
        updateId = new UpdateIdCommand(comEx);
        commandMap.put("help", help);
        commandMap.put("info", info);
        commandMap.put("show", show);
        commandMap.put("add", add);
        commandMap.put("update id", updateId);
        commandMap.put("remove_by_id", removeById);
        commandMap.put("clear", clear);
        commandMap.put("save", save);
        commandMap.put("execute_script", exScript);
        commandMap.put("exit", exit);
        commandMap.put("head", head);
        commandMap.put("add_if_min", addIfMin);
        commandMap.put("remove_lower", removeLower);
        commandMap.put("sum_of_price", sumOfPrice);
        commandMap.put("filter_greater_than_price", filterPrice);
        commandMap.put("print_descending", printDescending);
    }
    public void printCommandNotFound(){
        System.out.println("Команда не опознана. Введите help, чтобы вывести справку по доступным командам");
    }
    public void run() {
        while (this.scanner.hasNextLine()) {
            command = this.scanner.nextLine().trim();
            if (Pattern.matches("update id .+", command)) { //неудобная команда..
                try {
                    String jsonProduct = command.replace("update id ", "").strip();
                    updateId.setJsonProduct(jsonProduct);
                    updateId.execute();
                } catch (JsonSyntaxException e) {
                    System.out.println("Ошибка в описании продукта в формате json");
                }
            }
            else {
                String[] comSplit = command.split("\\s+", 2);
                if (commandMap.containsKey(comSplit[0])) {
                    if (comSplit.length == 1) {
                        switch (comSplit[0]) {
                            case ("help"):
                            case ("info"):
                            case ("show"):
                            case ("clear"):
                            case ("save"):
                            case ("exit"):
                            case ("head"):
                            case ("sum_of_price"):
                            case ("print_descending"):
                                commandMap.get(comSplit[0]).execute();
                                break;
                            default: printCommandNotFound();
                        }
                    } else {
                        switch (comSplit[0]) {
                            case ("add"):
                                try {
                                    String jsonProduct = comSplit[1].strip();
                                    add.setJsonProduct(jsonProduct);
                                    add.execute();
                                } catch (JsonSyntaxException e) {
                                    System.out.println("Ошибка в описании продукта в формате json");
                                }
                                break;
                            case ("add_if_min"):
                                try {
                                    String jsonProduct = comSplit[1].strip();
                                    addIfMin.setJsonProduct(jsonProduct);
                                    addIfMin.execute();
                                } catch (JsonSyntaxException e) {
                                    System.out.println("Ошибка в описании продукта в формате json");
                                }
                                break;
                            case ("remove_lower"):
                                try {
                                    String jsonProduct = comSplit[1].strip();
                                    removeLower.setJsonProduct(jsonProduct);
                                    removeLower.execute();
                                } catch (JsonSyntaxException e) {
                                    System.out.println("Ошибка в описании продукта в формате json");
                                }
                                break;
                            case ("remove_by_id"):
                                try {
                                    long id = Long.parseLong(comSplit[1]);
                                    removeById.setId(id);
                                    removeById.execute();
                                } catch (NumberFormatException e) {
                                    System.out.println("Значение id должно быть числом, не превышающим 9,223,372,036,854,775,807");
                                }
                                break;
                            case ("execute_script"):
                                exScript.setFileName(comSplit[1]);
                                exScript.execute();
                                break;
                            case ("filter_greater_than_price"):
                                try {
                                    long price = Long.parseLong(comSplit[1]);
                                    filterPrice.setPrice(price);
                                    filterPrice.execute();
                                } catch (NumberFormatException e) {
                                    System.out.println("Значение price должно быть числом, не превышающим 9,223,372,036,854,775,807");
                                }
                                break;
                        }
                    }
                }
                else {
                    printCommandNotFound();
                    }
                }
            }
        }
        public void setScanner(Scanner scanner){
            this.scanner = scanner;
        }
        public Scanner getScanner() {
            return this.scanner;
        }
}
