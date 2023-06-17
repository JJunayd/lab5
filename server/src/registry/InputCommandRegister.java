/**
 * Дочерний класс CommandRegister, читающий команды из командной строки
 */
package registry;

import commands.*;


public class InputCommandRegister extends CommandRegister {
    private Command handleNoArgCommand(NoArgCommand noArgCommand, String method) {
        NoArgCommand task = noArgCommandMap.get(method);
        task.setUser(noArgCommand.getUser());
        return task.execute();
    }

    private Command handleElementCommand(ElementCommand elementCommand, String method) {
        ElementCommand task = elementCommandMap.get(method);
        task.setElement(elementCommand.getElement());
        task.setAddArg(elementCommand.getAddArg());
        task.setUser(elementCommand.getUser());
        return task.execute();
    }

    private Command handleStringArgCommand(StringArgCommand stringArgCommand, String method) {
        StringArgCommand task = stringArgCommandMap.get(method);
        task.setArgument(stringArgCommand.getArgument());
        if(stringArgCommand.getAddArgument()!=null) {
            task.setAddArgument(stringArgCommand.getAddArgument());
        }
        task.setUser(stringArgCommand.getUser());
        return task.execute();
    }

    public Command handleCommand(Command command) {
        try {
            String method = command.getType().name().toLowerCase();
            if (noArgCommandMap.containsKey(method)) {
                return handleNoArgCommand((NoArgCommand) command, method);
            } else if (elementCommandMap.containsKey(method)) {
                return handleElementCommand((ElementCommand) command, method);
            } else if (stringArgCommandMap.containsKey(method)) {
                return handleStringArgCommand((StringArgCommand) command, method);
            }
            else {
                throw new NullPointerException();
            }
        }
            catch(NullPointerException e){
            return null;
        }
    }
}
