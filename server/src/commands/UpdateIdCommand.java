package commands;

import manager.CollectionManager;

public class UpdateIdCommand extends StringArgCommand{
        {type = CommandType.UPDATE_ID;}
        public UpdateIdCommand(){
        }
        @Override
        public Command execute() {
            StringArgCommand result = new StringArgCommand();
                if(CollectionManager.collection.stream().
                        noneMatch(p -> p.getId() == Long.parseLong(this.argument))) {
                    result.setMessage("Элемент не найден\n");
                }
                else if(CollectionManager.collection.stream().
                        noneMatch(p -> p.getId() == Long.parseLong(this.argument) && p.getCreator().equals(this.getUser()))) {
                    result.setMessage("У Вас нет прав на обновление этого элемента\n");
                }
                else{
                    result.setMessage(this.argument);
                }
            return result;
        }
    }
