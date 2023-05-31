/**
 * Команда, выводящая сумму цен всех элементов коллекции
 */
package commands;

import manager.CollectionManager;
import products.Product;

import java.util.Iterator;

public class SumOfPriceCommand extends NoArgCommand{
    {type = CommandType.SUM_OF_PRICE;}
    public SumOfPriceCommand(){}
    @Override
    public Command execute() {
        Command result = new NoArgCommand();
        long sum_of_price = CollectionManager.collection.stream().mapToLong(Product::getPrice).sum();
        result.setMessage("Сумма цен всех продуктов коллекции: " + sum_of_price + "\n");
        return result;
    }
}
