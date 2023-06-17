/**
 * Команда, выводящая элементы коллекции в порядке убывания
 */
package commands;


import manager.CollectionManager;

import java.util.Collections;

public class PrintDescendingCommand extends NoArgCommand{
    {type = CommandType.PRINT_DESCENDING;}
    public PrintDescendingCommand(){}
    @Override
    public Command execute() {
        Command result = new NoArgCommand();
        StringBuilder response = new StringBuilder();
        if(CollectionManager.getCollectionSize() == 0){
            result.setMessage("Коллекция пуста..\n");
        }
        else {
            CollectionManager.collection.stream().
                    sorted(Collections.reverseOrder()).
                    forEach((Product) -> response.append(Product).append("\n"));
            result.setMessage(response.toString());
        }
        return result;
    }
}