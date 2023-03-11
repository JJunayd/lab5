package collection;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class CollectionManager {
    public static final PriorityQueue<Product> collection;
    private static final ArrayList<Long> usedIds = new ArrayList<>();
    private static final ArrayList<Long> usedOrgIds = new ArrayList<>();
    private static final ZonedDateTime creationTime;
    public CollectionManager(){}
    static{
        collection = new PriorityQueue<>();
        creationTime = ZonedDateTime.now();
    }
    public static String getCollectionType(){
        return collection.getClass().getName();
    }
    public static int getCollectionSize(){
        return collection.size();
    }
    public static ZonedDateTime getCreationTime(){
        return creationTime;
    }
    public static PriorityQueue<Product> show(){return collection;}
    public static void add(Product product){
        while(usedIds.contains(product.getId())){
            product.updateId();
        }
        while(usedOrgIds.contains(product.getManufacturer().getId())){
            product.getManufacturer().updateId();
        }
        collection.add(product);
        usedIds.add(product.getId());
        usedOrgIds.add(product.getManufacturer().getId());
    }
    public static Iterator<Product> prodIter(){
        return CollectionManager.collection.iterator();
    }
    public static void remove(Product product){
        collection.remove(product);
    }
    public static void clear(){
        collection.clear();
    }
    public static Product head(){
        return collection.peek(); //если правильно понял..
    }
    public static Object[] toArray(){
        return collection.toArray();
    }
}

