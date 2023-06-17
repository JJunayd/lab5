package commands;

import manager.DatabaseManager;



public class RegisterCommand extends StringArgCommand{
    {type = CommandType.REGISTER;}
    public RegisterCommand(){}
    @Override
    public Command execute(){
        Command result = new StringArgCommand();
            String user = this.argument;
            String encPass = this.addArgument;
            boolean userRegistered = DatabaseManager.register(user, encPass);
            if(userRegistered){
                result.setMessage("Регистрация прошла успешно");
                result.setUser(user);
            }
            else{
                result.setMessage("Ошибка при регистрации");
            }
        return result;
    }
}

