/**
 * Команда, выводящая элементы коллекции в строковом представлении
 */
package commands;

import collection.CollectionManager;

public class ShowCommand implements NoArgCommand {
    @Override
    public void execute() {
        System.out.println(CollectionManager.show().toString());
    }
}
