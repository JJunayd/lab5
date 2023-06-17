/**
 * Команда, сохраняющая коллекцию в файл
 */
package commands;
import manager.CollectionManager;
import manager.DatabaseManager;

public class SaveCommand extends Command{
    {type = CommandType.SAVE;}
    public SaveCommand(){}

    public Command execute() {
        Command result = new NoArgCommand();
        try{
            DatabaseManager.clearAll();
            CollectionManager.collection.stream().forEach(DatabaseManager::addToDatabase);
            result.setMessage("Коллекция записана\n");
            return result;
        } catch (Exception e) {
            result.setMessage("Не удалось осуществить запись коллекции в файл\n");
            return result;
        }
    }
}
