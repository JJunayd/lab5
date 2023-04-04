/**
 * Команда, очищающая коллекцию
 */
package commands;

import collection.CollectionManager;

public class ClearCommand implements NoArgCommand {
    @Override
    public void execute() {
        CollectionManager.clear();
    }
}
