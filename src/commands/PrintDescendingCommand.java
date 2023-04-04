/**
 * Команда, выводящая элементы коллекции в порядке убывания
 */
package commands;


import collection.CollectionManager;

public class PrintDescendingCommand implements NoArgCommand {
    @Override
    public void execute() {
        Object[] arr = CollectionManager.toArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i]);
            if (i != 0) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}