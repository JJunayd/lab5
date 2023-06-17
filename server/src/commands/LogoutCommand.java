package commands;

import authorization.User;
import manager.DatabaseManager;

public class LogoutCommand extends NoArgCommand{
    {type = CommandType.LOGOUT;}
    public LogoutCommand(){}
    @Override
    public Command execute() {
        Command result = new StringArgCommand();
        User.logout();
        result.setMessage("Осуществлен выход из учетной записи");
        return result;
    }
}

