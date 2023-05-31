/**
 * Дочерний класс CommandRegister, читающий команды из командной строки
 */
package registry;

import commands.*;

import java.util.HashMap;
import java.util.Scanner;

public class InputCommandRegister extends CommandRegister {

    private Command handleElementCommand(ElementCommand elementCommand, String method) {
        ElementCommand task = elementCommandMap.get(method);
        task.setElement(elementCommand.getElement());
        return task.execute();
    }

    private Command handleStringArgCommand(StringArgCommand stringArgCommand, String method) {
        StringArgCommand task = stringArgCommandMap.get(method);
        task.setArgument(stringArgCommand.getArgument());
        return task.execute();
    }

    public Command handleCommand(Command command) {
        try {
            String method = command.getType().name().toLowerCase();
            if (noArgCommandMap.containsKey(method)) {
                return noArgCommandMap.get(method).execute();
            } else if (elementCommandMap.containsKey(method)) {
                return handleElementCommand((ElementCommand) command, method);
            } else if (stringArgCommandMap.containsKey(method)) {
                return handleStringArgCommand((StringArgCommand) command, method);
            }
            else {
                throw new NullPointerException();
            }
        }
            catch(NullPointerException e){return null;}
    }
}
