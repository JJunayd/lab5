/**
 * Команда, добавляющая элемент в коллекцию
 */
package commands;

import manager.CollectionManager;
import products.Product;

import static commands.CommandType.ADD;

public class AddCommand extends ElementCommand{
    {type = ADD;}
    public AddCommand(){}
    @Override
    public Command execute() {
        Command result = new ElementCommand();
        try {
            if (element.isValid) {
                CollectionManager.add(element);
                result.setMessage("Элемент добавлен\n");
                return result;
            }
            else {
                return null;
            }
        }
        catch(NullPointerException e){
            result.setMessage("Нарушен контракт\n");
            return result;
        }
    }
}
