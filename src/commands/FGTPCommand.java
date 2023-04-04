/**
 * Команда, выводящая элементы коллекции с ценой, больше заданной
 */
package commands;


import collection.CollectionManager;
import collection.Product;

import java.util.ArrayList;
import java.util.Iterator;

public class FGTPCommand implements ArgCommand {
    @Override
    public void execute(String arg) {
        try {
            long price = Long.parseLong(arg);
            ArrayList<Product> greaterPrice = new ArrayList<>();
            Iterator<Product> iter = CollectionManager.prodIter();

            while (iter.hasNext()) {
                if (iter.next().getPrice() > price) {
                    greaterPrice.add(iter.next());
                }
            }
            if (greaterPrice.isEmpty()) {
                System.out.println("В коллекции нет элементов с ценой, больше данной");
            } else {
                for (int i = 0; i < greaterPrice.size(); i++) {
                    System.out.print(greaterPrice.get(i));
                    if (i != greaterPrice.size() - 1) {
                        System.out.println(", ");
                    }
                }
            }
            greaterPrice.clear();
        } catch (NumberFormatException e) {
            System.out.println("Значение price должно быть числом, не превышающим 9,223,372,036,854,775,807");
        }
    }
}
