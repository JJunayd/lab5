/**
 * Команда, выводящая информацию о коллекции
 */
package commands;


import manager.CollectionManager;

public class InfoCommand extends NoArgCommand{
    {type = CommandType.INFO;}
    public InfoCommand(){}
    @Override
    public Command execute() {
        Command result = new NoArgCommand();
        result.setMessage("Тип коллекции: " + CollectionManager.getCollectionType() +
                "\nДата инициализации: " + CollectionManager.getCreationTime()+
                "\nЧисло элементов коллекции: " + CollectionManager.getCollectionSize()+
                "\n");
        return result;
    }
}

