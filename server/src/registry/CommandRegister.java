/**
 * Абстрактный класс, читающий и исполняющий команды
 */
package registry;
import commands.*;

import java.util.HashMap;
import java.util.Scanner;

public abstract class CommandRegister {
    HashMap<String, NoArgCommand> noArgCommandMap = new HashMap<>();
    HashMap<String, ElementCommand> elementCommandMap = new HashMap<>();
    HashMap<String, StringArgCommand> stringArgCommandMap = new HashMap<>();
       {noArgCommandMap.put("help", new HelpCommand());
        noArgCommandMap.put("info", new InfoCommand());
        noArgCommandMap.put("show", new ShowCommand());
        noArgCommandMap.put("clear", new ClearCommand());
        noArgCommandMap.put("exit", new ExitCommand());
        noArgCommandMap.put("head", new HeadCommand());
        noArgCommandMap.put("sum_of_price", new SumOfPriceCommand());
        noArgCommandMap.put("print_descending", new PrintDescendingCommand());

        elementCommandMap.put("add", new AddCommand());
        elementCommandMap.put("update_id", new UpdateIdCommand());
        elementCommandMap.put("add_if_min", new AddIfMinCommand());
        elementCommandMap.put("remove_lower", new RemoveLowerCommand());

        stringArgCommandMap.put("remove_by_id", new RemoveByIdCommand());
        //stringArgCommandMap.put("execute_script", new ExecuteScriptCommand());
        stringArgCommandMap.put("filter_greater_than_price", new FGTPCommand());}
}