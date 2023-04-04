/**
 * Команда, исполняющая скрипт
 */
package commands;


import commands.registry.ScriptRegister;
import exceptions.ScriptRecursionException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ExecuteScriptCommand implements ArgCommand {
    private String fileName;
    private boolean recExcThrown(String fileName) {
        boolean thrown = false;
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
                                    thrown = true;
                                    break;
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Не удалось прочитать файл");
                    }
                    if (thrown) {
                        break;
                    }
                }
            }
            scanner.close();
        }catch(IOException e) {
            System.out.println("Не удалось прочитать файл");
        }
        return thrown;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void execute(String arg) {
        setFileName(arg);
        if (recExcThrown(fileName)) {
            try {
                throw new ScriptRecursionException("рекурсия..");
            } catch (ScriptRecursionException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try (Reader reader = new InputStreamReader(new FileInputStream(fileName), Charset.defaultCharset())) {
                Scanner scanner = new Scanner(reader);
                ScriptRegister scriptRegister = new ScriptRegister();
                scriptRegister.setScanner(scanner);
                scriptRegister.run();
            }
            catch(IOException e){
                System.out.println("Не удалось прочитать файл");
            }
        }
    }
}
