/**
 * Команда, выводящая первый элемент коллекции
 */
package commands;


import manager.CollectionManager;

import static commands.CommandType.HEAD;

public class HeadCommand extends NoArgCommand{
    {type = HEAD;}
    public HeadCommand(){}
    @Override
    public Command execute() {
        Command result = new NoArgCommand();
        if(CollectionManager.head() != null) {
            result.setMessage(CollectionManager.head()+"\n");
        }
        else{
            result.setMessage("Коллекция пуста..\n");
        }
        return result;
    }
}
