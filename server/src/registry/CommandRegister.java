/**
 * Абстрактный класс, читающий и исполняющий команды
 */
package registry;
import commands.*;

import java.util.HashMap;

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
        noArgCommandMap.put("logout", new LogoutCommand());

        elementCommandMap.put("add", new AddCommand());
        elementCommandMap.put("update_task", new UpdateTaskCommand());
        elementCommandMap.put("add_if_min", new AddIfMinCommand());
        elementCommandMap.put("remove_lower", new RemoveLowerCommand());

        stringArgCommandMap.put("remove_by_id", new RemoveByIdCommand());
        stringArgCommandMap.put("update_id", new UpdateIdCommand());
        stringArgCommandMap.put("filter_greater_than_price", new FGTPCommand());
        stringArgCommandMap.put("login", new LoginCommand());
        stringArgCommandMap.put("register", new RegisterCommand());}
}