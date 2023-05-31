/**
 * Команда, выводящая элементы коллекции в строковом представлении
 */
package commands;

import manager.CollectionManager;

public class ShowCommand extends NoArgCommand{
    {type = CommandType.SHOW;}
    public ShowCommand(){}
    @Override
        public Command execute() {
        Command result = new NoArgCommand();
        StringBuilder response = new StringBuilder();
        if(CollectionManager.getCollectionSize() != 0) {
            CollectionManager.collection.stream().forEachOrdered(Product->response.append(Product).append("\n"));
        }
        else{
            response.append("Коллекция пуста.\n");
            }
        result.setMessage(String.valueOf(response));
        return result;
    }
}
