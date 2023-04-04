/**
 * Команда, удаляющая из коллекции элементы, меньше заданного
 */
package commands;
import collection.CollectionManager;
import collection.Product;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

public class RemoveLowerCommand extends NewElementCommand implements ArgCommand{
    @Override
    public void execute(String arg) {
        try {
            setJsonProduct(arg);
            Product product = fromJson(jsonProduct);
            if(product.isValid){
                ArrayList<Product> temp = new ArrayList<>();
                Iterator<Product> iter = CollectionManager.prodIter();
                while (iter.hasNext()) {
                    if (iter.next().toString().equals(product.toString())) {
                        temp.add(product);
                        while (CollectionManager.head().getId() != product.getId()){
                            CollectionManager.collection.poll();
                        }
                    }
                }
                if (temp.isEmpty()) {
                    System.out.println("Элемент не найден");
                }
                temp.clear();
            }
        } catch (JsonSyntaxException e) {
            System.out.println("Ошибка в описании продукта в формате json");
        }
    }
}
