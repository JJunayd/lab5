/**
 * Команда, удаляющая из коллекции элементы, меньше заданного
 */
package commands;
import manager.CollectionManager;
import products.Product;
import java.util.Iterator;

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
            else {
                CollectionManager.collection.stream().
                        filter(p -> p.compareTo(this.element) < 0).
                        forEach(CollectionManager::remove);
                result.setMessage("Элементы, меньше заданного, удалены\n");
            }
            return result;
        }
        catch(NullPointerException e){
            result.setMessage("Нарушен контракт\n");
            return result;
        }
    }
}
