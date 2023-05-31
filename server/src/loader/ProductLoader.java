package loader; /**
 * Класс, загружающий исходные элементы из файла в коллекцию
 */
import manager.CollectionManager;
import products.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import serverExceptions.PathNotSetException;
import parser.ProductConverter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;
public class ProductLoader {
    private static String path;
    static{
        try{
            path=System.getenv("LAB_5_PATH");
            if(path==null){
                throw new PathNotSetException("Переменная окружения \"LAB_5_PATH\" не установлена");
            }
        }
        catch(PathNotSetException e){
            System.out.println(e.getMessage());
        }
    }
    public static void load() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Product.class, new ProductConverter());
        Gson gson = builder.create();
        try (Reader reader = new InputStreamReader(new FileInputStream(path), Charset.defaultCharset())) {
            Product[] products = gson.fromJson(reader, Product[].class);
            for (Product product : products) {
                if(product != null) {
                    product.updateId();
                    product.updateOrgId();
                    product.setCreationDate(ZonedDateTime.now());
                    CollectionManager.add(product);
                }
            }
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл");
        }
        catch(NullPointerException e){
            System.out.println("Файл пустой..");
        }
        catch(JsonSyntaxException e){
            System.out.println("Некорректный формат файла");
        }
    }
}