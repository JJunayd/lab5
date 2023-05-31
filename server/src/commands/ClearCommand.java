/**
 * Команда, очищающая коллекцию
 */
package commands;

import manager.CollectionManager;

import static commands.CommandType.CLEAR;

public class ClearCommand extends NoArgCommand{
    {type = CLEAR;}
    public ClearCommand(){
    }
    @Override
    public Command execute() {
        Command result = new NoArgCommand();
        CollectionManager.clear();
        result.setMessage("Коллекция очищена\n");
        return result;
    }
}
