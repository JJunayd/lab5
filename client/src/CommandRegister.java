import authorization.User;
import commands.*;
import products.Product;

import java.util.*;

public class CommandRegister {
    Scanner scanner;
    final static HashSet<String> noArgCommands = new HashSet<>(Arrays.asList("clear", "exit", "head", "help", "info", "print_descending", "show", "sum_of_price", "logout"));
    final static HashSet<String> ElementCommands = new HashSet<>(Arrays.asList("add", "add_if_min", "remove_lower"));
    final static HashSet<String> StringArgCommands = new HashSet<>(Arrays.asList("execute_script", "remove_by_id", "update_id", "filter_greater_than_price"));
    final static int COMMAND_INDEX = 2;
    final static int ARG_COMMAND_LENGTH = 2;
    public void setScanner(Scanner scanner){
        this.scanner = scanner;
    }
    public boolean hasRequest(){
        return this.scanner.hasNextLine();
    }
    public <T extends Command> T getRequest() {
            String command = this.scanner.nextLine().trim();
            String[] comSplit = command.split("\\s+", COMMAND_INDEX);
            String method = comSplit[0];
            T request = null;
            if (noArgCommands.contains(method)) {
                request = (T) new NoArgCommand(CommandType.valueOf(method.toUpperCase()));
                request.setUser(User.current());
            }
            else if(ElementCommands.contains(method)) {
                Product argument = NewElement.create();
                request = (T) new ElementCommand(CommandType.valueOf(method.toUpperCase()), argument);
                request.setUser(User.current());
            }
            else if(StringArgCommands.contains(method) && comSplit.length>=ARG_COMMAND_LENGTH) {
                String argument = comSplit[1].strip();
                request = (T) new StringArgCommand(CommandType.valueOf(method.toUpperCase()), argument);
                request.setUser(User.current());
            }
            else if(method.equals("login")){
                request = (T) User.loginCreds();
            }
            else if(method.equals("register")){
                request = (T) User.regCreds();
            }
            return request;
        }
    }
