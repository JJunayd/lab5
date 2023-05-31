/**
 * Команда, сохраняющая коллекцию в файл
 */
package commands;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import manager.CollectionManager;
import parser.ProductConverter;
import products.Product;
import serverExceptions.PathNotSetException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand extends Command{
    {type = CommandType.SAVE;}
    public SaveCommand(){}
    private static String saveToPath;
    private final GsonBuilder builder = new GsonBuilder();
    {builder.registerTypeAdapter(Product.class, new ProductConverter());}

    Gson gson = builder.create();
    static{
        try{
            saveToPath=System.getenv("LAB_5_PATH");
            if(saveToPath==null){
                throw new PathNotSetException("Переменная окружения \"LAB_5_PATH\" не установлена");
            }
        }
        catch(PathNotSetException e){
            System.out.println(e.getMessage());
        }
    }

    public Command execute() {
        Command result = new NoArgCommand();
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
            result.setMessage("Коллекция записана\n");
            return result;
        } catch (IOException e) {
            result.setMessage("Не удалось осуществить запись коллекции в файл\n");
            return result;
        }
    }
}
