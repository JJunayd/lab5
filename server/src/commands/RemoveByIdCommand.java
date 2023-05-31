/**
 * Команда, удаляющая элемент коллекции с заданным идентификатором
 */
package commands;


import manager.CollectionManager;

public class RemoveByIdCommand extends StringArgCommand{
    {type = CommandType.REMOVE_BY_ID;}
    public RemoveByIdCommand(){

    }
    public Command execute() {
        Command result = new StringArgCommand();
       try {
           long id = Long.parseLong(argument);
           if(CollectionManager.collection.stream().
                   noneMatch(p -> p.getId() == id)){
               result.setMessage("В коллекции нет элемента с таким идентификатором\n");
           }
           CollectionManager.collection.stream().filter(p -> p.getId() == id).forEach(CollectionManager::remove);
           result.setMessage("Элемент удален\n");
       } catch (NumberFormatException e) {
           result.setMessage("Значение id должно быть числом, не превышающим 9,223,372,036,854,775,807\n");
       }
       return result;
   }
}

