/**
 * Команда, выводящая элементы коллекции с ценой, больше заданной
 */
package commands;


import manager.CollectionManager;
import static commands.CommandType.FILTER_GREATER_THAN_PRICE;

public class FGTPCommand extends StringArgCommand{
    {type = FILTER_GREATER_THAN_PRICE;}
    public FGTPCommand(){

    }
    @Override
    public Command execute() {
        Command result = new StringArgCommand();
        try {
            long price = Long.parseLong(argument);
            StringBuilder response = new StringBuilder();
            if(CollectionManager.collection.stream().
                    noneMatch(p -> p.getPrice() > price)){
                result.setMessage("В коллекции нет элементов с ценой, больше данной\n");
            }
            else {
                CollectionManager.collection.stream().
                        filter(s -> s.getPrice() > price).
                        forEachOrdered((Product -> response.append(Product).append("\n")));
                result.setMessage(response.toString());
            }
            return result;
        } catch (NumberFormatException e) {
            result.setMessage("Значение price должно быть числом, не превышающим 9,223,372,036,854,775,807\n");
            return result;
        }
    }
}
