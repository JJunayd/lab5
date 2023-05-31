import commands.Command;
import commands.CommandType;
import commands.ElementCommand;
import commands.StringArgCommand;
import products.Product;

import java.io.*;
import java.net.InetAddress;
import java.net.JarURLConnection;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Класс, осуществляющий запуск программы
 */



public class Client {
    public static void main(String[] args) {
        CommandRegister consoleReg = new CommandRegister();
        consoleReg.setScanner(new Scanner(System.in));
        System.out.println("Введите команду или введите help для просмотра доступных команд");
        while(consoleReg.hasRequest()) {
            try (Socket socket = new Socket(InetAddress.getLocalHost(), 6969);
                 ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream())) {

                 Command request = consoleReg.getRequest();

                 if(request!=null && request.getType().equals(CommandType.EXECUTE_SCRIPT)){

                     StringArgCommand exScript = (StringArgCommand)request;
                     String scriptPath = exScript.getArgument();
                     ScriptRegister scriptReg = new ScriptRegister(scriptPath);
                     try (Reader scriptReader = new InputStreamReader(new FileInputStream(scriptPath), Charset.defaultCharset())) {
                         Scanner scriptScanner = new Scanner(scriptReader);
                         scriptReg.setScanner(scriptScanner);
                         socket.close();
                     while(scriptReg.hasRequest()) {
                         try (Socket socket2 = new Socket(InetAddress.getLocalHost(), 6969);
                              ObjectInputStream reader2 = new ObjectInputStream(socket2.getInputStream());
                              ObjectOutputStream writer2 = new ObjectOutputStream(socket2.getOutputStream())) {

                             Command scriptRequest = scriptReg.getRequest();
                             writer2.writeObject(scriptRequest);
                             Command scriptResponse = (Command)reader2.readObject();
                             System.out.println(scriptResponse.getMessage());
                             if (scriptRequest != null && scriptRequest.getType().equals(CommandType.EXIT)) {
                                 System.exit(0);
                             }
                         }
                     }
                     System.out.println("Введите команду или введите help для просмотра доступных команд");
                     }catch(Exception e){
                         System.out.println("Не удалось прочитать скрипт");
                     }
                 }
                 else {
                     writer.writeObject(request);
                     try{
                         Command response = null;
                         try {
                             response = (Command)reader.readObject();
                         } catch (ClassNotFoundException e) {
                             System.out.println("Ошибка при чтении ответа сервера");
                         }
                         System.out.println(response.getMessage());
                     }catch(EOFException e){
                         System.out.println("Команда не опознана. Введите help, чтобы вывести справку по доступным командам");
                     }
                     if (request != null && request.getType().equals(CommandType.EXIT)) {
                         System.exit(0);
                     }
                     else{
                         System.out.println("\nВведите команду или введите help для просмотра доступных команд");
                     }
                 }
            } catch (IOException e) {
                 System.out.println("Ошибка при подключении..");
            }
        }
    }
}