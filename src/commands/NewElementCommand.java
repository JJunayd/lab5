/**
 * Абстрактный класс для команд, создающих новые элементы
 */
package commands;

import collection.Product;


import java.time.ZonedDateTime;

public abstract class NewElementCommand extends GsonCommand{
    public Product newProduct(){
        Product product = fromJson(jsonProduct);
        product.updateId();
        product.setCreationDate(ZonedDateTime.now());
        return product;
    }
}
