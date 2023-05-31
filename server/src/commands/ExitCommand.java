/**
 * Команда, завершающая работу программы
 */
package commands;

import static commands.CommandType.EXIT;

public class ExitCommand extends NoArgCommand{
    {type = EXIT;}
    public ExitCommand(){
    }
    public static final String exitMessage = "Завершение работы..\n";
    @Override
    public Command execute() {
        Command result = new NoArgCommand();
        result.setMessage(exitMessage);
        return result;
    }
}
