package commands;


public class ExecuteScriptCommand implements Executable {
    private final CommandExecuter comEx;
    public ExecuteScriptCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    private String fileName;
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public String getFileName(){
        return this.fileName;
    }
    @Override
    public void execute() {
        comEx.exScript(fileName);
    }
}
