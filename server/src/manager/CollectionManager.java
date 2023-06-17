/**
 * Класс, работающий с коллекцией
 */
package manager;

import products.Product;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.PriorityBlockingQueue;

public class CollectionManager {
    public static final PriorityBlockingQueue<Product> collection;
    private static final Date creationTime;
    public CollectionManager(){}
    static{
        collection = new PriorityBlockingQueue<>();
        creationTime = Date.from(Instant.now());
    }
    public static String getCollectionType(){
        return collection.getClass().getName();
    }
    public static int getCollectionSize(){
        return collection.size();
    }
    public static Date getCreationTime(){
        return creationTime;
    }
    public static void add(Product product){
        collection.add(product);
    }
    public static void remove(Product product){
        collection.remove(product);
    }
    public static void clear(){
        collection.clear();
    }
    public static Product head(){
        return collection.peek();
    }
}

