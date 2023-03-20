package commands;


public class ExecuteScriptCommand implements ArgCommand {
    private final CommandExecuter comEx;
    public ExecuteScriptCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    private String fileName;
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void execute(String arg) {
        setFileName(arg);
        comEx.exScript(fileName);
    }
}
