/**
 * Дочерний класс CommandRegister, читающий команды из файла
 */
package registry;

import commands.*;
import java.util.Scanner;

public class ScriptRegister extends CommandRegister {
    private Scanner scanner;
    final static int COMMAND_INDEX = 2;
    final static int ARG_COMMAND_LENGTH = 2;

    public ScriptRegister(Scanner reader) {
        this.scanner = reader;
    }

    private Command string2Command(String command) {
        Command task = null;
        String[] comSplit = command.split("\\s+", COMMAND_INDEX);
        String method = comSplit[0];
        if (noArgCommandMap.containsKey(method)) {
            task = new NoArgCommand(CommandType.valueOf(method.toUpperCase()));
        } else if (elementCommandMap.containsKey(method)) {
            //а как.
        }
            else if(stringArgCommandMap.containsKey(method)){
                String argument = comSplit[1].strip();
                task = new StringArgCommand(CommandType.valueOf(method.toUpperCase()), argument);
            }
            return task;
    }
    public String run(){
        InputCommandRegister comReg = new InputCommandRegister();
        StringBuilder result = new StringBuilder();
        while(this.scanner.hasNextLine()){
            String task = this.scanner.nextLine();
            Command command = string2Command(task);
            result.append(comReg.handleCommand(command));
            if(this.scanner.hasNextLine()){
                result.append("\n");
            }
        }
        return String.valueOf(result);
    }
}
