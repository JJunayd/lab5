/**
 * Команда, удаляющая элемент коллекции с заданным идентификатором
 */
package commands;


import collection.CollectionManager;
import collection.Product;

import java.util.Iterator;

public class RemoveByIdCommand implements ArgCommand{
    public void execute(String arg) {
       try {
           long id = Long.parseLong(arg);
           Iterator<Product> iter = CollectionManager.prodIter();
           while (iter.hasNext()) {
               Product product = iter.next();
               if (product.getId() == id) {
                   CollectionManager.remove(product);
               }
           }
       } catch (NumberFormatException e) {
           System.out.println("Значение id должно быть числом, не превышающим 9,223,372,036,854,775,807");
       }
   }
}

