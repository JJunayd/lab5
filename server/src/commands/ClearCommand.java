/**
 * Команда, очищающая коллекцию
 */
package commands;

import manager.CollectionManager;
import manager.DatabaseManager;

import static commands.CommandType.CLEAR;

public class ClearCommand extends NoArgCommand{
    {type = CLEAR;}
    public ClearCommand(){
    }
    @Override
    public Command execute() {
        Command result = new NoArgCommand();
        if(DatabaseManager.clear(this.getUser())) {
            CollectionManager.clear();
            DatabaseManager.loadCollection();
            result.setMessage("Коллекция очищена\n");
        }
        else{result.setMessage("Не удалось очистить коллекцию\n");}
        return result;
    }
}
