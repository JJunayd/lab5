package commands;


public class RemoveByIdCommand implements ArgCommand{
    private final CommandExecuter comEx;
    public RemoveByIdCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }

    public void execute(String arg) {
       try {
           long id = Long.parseLong(arg);
           comEx.removeById(id);
       } catch (NumberFormatException e) {
           System.out.println("Значение id должно быть числом, не превышающим 9,223,372,036,854,775,807");
       }
   }
}

