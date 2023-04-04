/**
 * Команда, выводящая информацию о коллекции
 */
package commands;


import collection.CollectionManager;

public class InfoCommand implements NoArgCommand {
    @Override
    public void execute() {
        System.out.println("Тип коллекции: " + CollectionManager.getCollectionType());
        System.out.println("Дата инициализации: " + CollectionManager.getCreationTime());
        System.out.println("Число элементов коллекции: " + CollectionManager.getCollectionSize());
    }
}

