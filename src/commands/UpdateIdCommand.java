/**
 * Команда, обновляющая идентификатор заданного элемента коллекции
 */
package commands;

import collection.CollectionManager;
import collection.Product;
import com.google.gson.JsonSyntaxException;

import java.util.Iterator;

public class UpdateIdCommand extends NewElementCommand implements ArgCommand{
    @Override
    public void execute(String arg) {
        try {
            setJsonProduct(arg);
            if(newProduct().isValid) {
                Iterator<Product> iter = CollectionManager.prodIter();
                while (iter.hasNext()) {
                    Product nextProduct = iter.next();
                    while (nextProduct.getId() == newProduct().getId()) {
                        nextProduct.updateId();
                    }
                }
            }
        } catch (JsonSyntaxException e) {
            System.out.println("Ошибка в описании продукта в формате json");
        }
    }
}
