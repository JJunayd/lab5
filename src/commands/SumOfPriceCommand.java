/**
 * Команда, выводящая сумму цен всех элементов коллекции
 */
package commands;

import collection.CollectionManager;
import collection.Product;

import java.util.Iterator;

public class SumOfPriceCommand implements NoArgCommand {

    @Override
    public void execute() {
        long sum_of_price = 0;
        Iterator<Product> iter = CollectionManager.prodIter();
        while (iter.hasNext()) {
            sum_of_price += iter.next().getPrice();
        }
        System.out.println("Сумма цен всех продуктов коллекции: " + sum_of_price);
    }
}
