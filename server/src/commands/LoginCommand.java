package commands;

import manager.DatabaseManager;



public class LoginCommand extends StringArgCommand{
    {type = CommandType.LOGIN;}
    public LoginCommand(){}
    @Override
    public Command execute() {
        Command result = new StringArgCommand();
            String user = this.argument;
            String encPass = this.addArgument;
            boolean userFound = DatabaseManager.login(user, encPass);
            if (userFound) {
                result.setMessage("Авторизация прошла успешно");
                result.setUser(user);
            } else {
                result.setMessage("Пользователь не найден");
            }
        return result;
    }
}
