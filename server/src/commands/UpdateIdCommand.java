/**
 * Команда, обновляющая идентификатор заданного элемента коллекции
 */
package commands;

import manager.CollectionManager;
import products.Product;

import java.util.Iterator;

public class UpdateIdCommand extends ElementCommand{
    {type = CommandType.UPDATE_ID;}
    public UpdateIdCommand(){

    }
    @Override
    public Command execute() {
        Command result = new ElementCommand();
        try {
            if (element.isValid) {
                if (CollectionManager.collection.stream().
                        noneMatch(p -> p.equals(element))) {
                    result.setMessage("Элемент не найден\n");
                } else {
                    CollectionManager.collection.stream().
                            filter(p -> p.equals(element)).
                            forEach(Product::updateId);
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
