/**
 * Команда, удаляющая из коллекции элементы, меньше заданного
 */
package commands;
import manager.CollectionManager;
import manager.DatabaseManager;
public class RemoveLowerCommand extends ElementCommand{
    {type = CommandType.REMOVE_LOWER;}
    public RemoveLowerCommand(){

    }
    @Override
    public Command execute() {
        Command result = new ElementCommand();
        try {
            if(CollectionManager.collection.stream().
                    noneMatch(p -> p.compareTo(this.element) < 0)){
                result.setMessage("Элемент не найден\n");
            }
            else if(CollectionManager.collection.stream().
                    noneMatch(p -> p.compareTo(this.element) < 0 && p.getCreator().equals(this.getUser()))){
                result.setMessage("У Вас нет прав на удаление этих элементов\n");
            }
            else {
                CollectionManager.collection.stream().
                        filter(p -> p.compareTo(this.element) < 0 && p.getCreator().equals(this.getUser())).
                        forEach(DatabaseManager::removeFromDatabase);
                CollectionManager.clear();
                DatabaseManager.loadCollection();
                result.setMessage("Доступные для изменения элементы, меньше заданного, удалены\n");
            }
            return result;
        }
        catch(NullPointerException e){
            result.setMessage("Нарушен контракт\n");
            return result;
        }
    }
}
