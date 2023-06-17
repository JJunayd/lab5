/**
 * Команда, добавляющая элемент в коллекцию
 */
package commands;

import manager.CollectionManager;
import manager.DatabaseManager;

import static commands.CommandType.ADD;

public class AddCommand extends ElementCommand{
    {type = ADD;}
    public AddCommand(){}
    @Override
    public Command execute() {
        Command result = new ElementCommand();
        try {
            if (element.isValid) {
                if(DatabaseManager.addToDatabase(element)){
                    CollectionManager.clear();
                    DatabaseManager.loadCollection();
                    result.setMessage("Элемент добавлен\n");
                }
                else{
                    result.setMessage("Не удалось добавить элемент\n");
                }
                return result;
            }
            else {
                return null;
            }
        }
        catch(NullPointerException e){
            e.printStackTrace();
            result.setMessage("Нарушен контракт\n");
            return result;
        }
    }
}
