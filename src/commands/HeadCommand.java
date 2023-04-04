/**
 * Команда, выводящая первый элемент коллекции
 */
package commands;


import collection.CollectionManager;

public class HeadCommand implements NoArgCommand {
    @Override
    public void execute() {
        System.out.println(CollectionManager.head());
    }
}
