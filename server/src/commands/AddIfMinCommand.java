/**
 * Команда, добавляющая элемент в коллекцию, если его значение меньше минимального значения в данной коллекции
 */
package commands;

import manager.CollectionManager;
import products.Product;

import static commands.CommandType.ADD;
import static commands.CommandType.ADD_IF_MIN;

public class AddIfMinCommand extends ElementCommand{
    {type = ADD_IF_MIN;}
    public AddIfMinCommand(){
    }
    @Override
    public Command execute() {
        Command result = new ElementCommand();
        try {
            if (element.isValid) {
                if (element.compareTo(CollectionManager.head()) < 0) {
                    CollectionManager.add(element);
                    result.setMessage("Элемент добавлен\n");
                } else {
                    result.setMessage("Элемент не добавлен\n");
                }
                return result;
            }
            else{return null;}
        }
        catch(NullPointerException e){
            result.setMessage("Элемент не добавлен\n");
            return result;
        }
    }
}
