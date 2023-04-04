/**
 * Команда, добавляющая элемент в коллекцию
 */
package commands;

import collection.CollectionManager;
import com.google.gson.JsonSyntaxException;

public class AddCommand extends NewElementCommand implements ArgCommand{
    @Override
    public void execute(String arg) {
        try {
            setJsonProduct(arg);
            if(newProduct().isValid) {
                CollectionManager.add(newProduct());
            }
        } catch (JsonSyntaxException e) {
            System.out.println("Ошибка в описании продукта в формате json");
        }
    }
}
