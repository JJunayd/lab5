package commands;
import exceptions.PathNotSetException;

public class SaveCommand extends GsonCommand implements Executable{
    private final CommandExecuter comEx;
    public SaveCommand(CommandExecuter comEx){
        this.comEx = comEx;
    }
    private static String saveToPath;
    static{
        try{
            saveToPath=System.getenv("LAB_5_SAVE_PATH");
            if(saveToPath==null){
                throw new PathNotSetException("Переменная окружения \"LAB_5_SAVE_PATH\" не установлена");
            }
        }
        catch(PathNotSetException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void execute() {
        comEx.save(saveToPath, gson);
    }
}
