/**
 * Команда, обновляющая идентификатор заданного элемента коллекции
 */
package commands;

import manager.CollectionManager;
import manager.DatabaseManager;

public class UpdateTaskCommand extends ElementCommand{
    {type = CommandType.UPDATE_TASK;}
    public UpdateTaskCommand(){
    }
    @Override
    public Command execute() {
        Command result = new ElementCommand();
        try {
            if (element.isValid) {
                    if (CollectionManager.collection.stream().
                            noneMatch(p -> p.equals(element) && p.getId() == Long.parseLong(this.addArg))) {
                        result.setMessage("Элемент не найден\n");
                    } else {
                        CollectionManager.collection.stream().
                                filter(p -> p.equals(element) && p.getId() == Long.parseLong(this.addArg) ).
                                forEach(DatabaseManager::updateId);
                        CollectionManager.clear();
                        DatabaseManager.loadCollection();
                        result.setMessage("Элемент обновлен\n");
                    }
                    return result;
                }
                else{return null;}
        }
        catch(NullPointerException e){
            result.setMessage("Нарушен контракт\n");
            return result;
        }
    }
}
