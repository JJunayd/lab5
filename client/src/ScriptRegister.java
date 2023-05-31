import commands.*;
import products.Product;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ScriptRegister extends CommandRegister{
    private String fileName;
    public ScriptRegister(String fileName){
        this.fileName = fileName;
    }
    private String excMessage() throws NullPointerException {
        boolean rec = false;
        try (Reader reader = new InputStreamReader(new FileInputStream(fileName), Charset.defaultCharset())) {
            Path file = Path.of(fileName);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine().trim();
                if (Pattern.matches("execute_script .+", command)) {
                    String fileName1 = command.replace("execute_script ", "");
                    try (Reader reader1 = new InputStreamReader((new FileInputStream(fileName1)), Charset.defaultCharset())) {
                        Scanner scanner1 = new Scanner(reader1);
                        while (scanner1.hasNextLine()) {
                            String command1 = scanner1.nextLine().trim();
                            if (Pattern.matches("execute_script .+", command1)) {
                                Path file1 = Path.of(command1.replace("execute_script ", ""));
                                if (Files.mismatch(file, file1) == -1) {
                                    rec = true;
                                    break;
                                }
                            }
                        }
                    } catch (IOException e) {
                        return "Не удалось прочитать файл скрипта, вызываемого в данном";
                    }
                    if (rec) {
                        break;
                    }
                }
            }
            scanner.close();
        }catch(IOException e) {
            return "Не удалось прочитать файл..";
        }
        if(rec){
            return "рекурсия..";
        }
        else{
            return null;
        }
    }
    private boolean excThrown(){
        if (excMessage()!=null){
            return true;
        }
        else{return false;}
    }
    public boolean hasRequest(){
        if(!excThrown()) {
            return this.scanner.hasNextLine();
        }
        else{
            System.out.println(excMessage());
            System.out.println("\nВведите команду или введите help для просмотра доступных команд\n");
            return false;
        }
    }
}
