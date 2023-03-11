package commands;


public class RemoveByIdCommand implements Executable{
    private final CommandExecuter comEx;
    public RemoveByIdCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
   private long id;
   public void execute() {
       comEx.removeById(id);
   }
    public void setId(long id) {
        this.id = id;
    }
}

