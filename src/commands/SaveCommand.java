/**
 * Команда, сохраняющая коллекцию в файл
 */
package commands;
import collection.CollectionManager;
import exceptions.PathNotSetException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand extends GsonCommand implements NoArgCommand {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveToPath))) {
            Object[] arr = CollectionManager.toArray();
            if(arr.length == 0){
                writer.write("");
            }
            else if (arr.length == 1) {
                writer.write(gson.toJson(arr[0]));
            } else {
                writer.write(gson.toJson(arr));
            }
        } catch (IOException e) {
            System.out.println("Не удалось осуществить запись в файл");
        }
    }
}
