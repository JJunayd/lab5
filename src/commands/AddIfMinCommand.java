/**
 * Команда, добавляющая элемент в коллекцию, если его значение меньше минимального значения в данной коллекции
 */
package commands;

import collection.CollectionManager;
import com.google.gson.JsonSyntaxException;

public class AddIfMinCommand extends NewElementCommand implements ArgCommand{
    public void execute(String arg) {
        try {
            setJsonProduct(arg);
            if (newProduct().isValid) {
                if (newProduct().compareTo(CollectionManager.head()) < 0) {
                    CollectionManager.add(newProduct());
                }
            }
        } catch (JsonSyntaxException e) {
            System.out.println("Ошибка в описании продукта в формате json");
        }
    }
}
