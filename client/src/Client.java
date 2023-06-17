import authorization.User;
import commands.Command;
import commands.CommandType;
import commands.ElementCommand;
import commands.StringArgCommand;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Класс, осуществляющий запуск программы
 */



public class Client {
    private static int port = 9703;
    public static void main(String[] args) {
        CommandRegister consoleReg = new CommandRegister();
        consoleReg.setScanner(new Scanner(System.in));
        System.out.println("Введите команду login для авторизации или register для регистрации нового пользователя" +
                "\nВведите команду help для просмотра доступных команд");
        while(consoleReg.hasRequest()) {
            try (Socket socket = new Socket(InetAddress.getLocalHost(), port);
                 ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream())) {

                Command request = consoleReg.getRequest();

                if(request!=null && !User.authorized && !request.getType().equals(CommandType.LOGIN) && !request.getType().equals(CommandType.REGISTER) && !request.getType().equals(CommandType.HELP)){
                    System.out.println("Вы не авторизованы. Введите команду login для авторизации или register для регистрации нового пользователя" +
                            "\nВведите команду help для просмотра доступных коман");
                }
                else {
                    if (request != null && request.getType().equals(CommandType.EXECUTE_SCRIPT)) {
                        StringArgCommand exScript = (StringArgCommand) request;
                        String scriptPath = exScript.getArgument();
                        ScriptRegister scriptReg = new ScriptRegister(scriptPath);
                        try (Reader scriptReader = new InputStreamReader(new FileInputStream(scriptPath), Charset.defaultCharset())) {
                            Scanner scriptScanner = new Scanner(scriptReader);
                            scriptReg.setScanner(scriptScanner);
                            socket.close();
                            while (scriptReg.hasRequest()) {
                                try (Socket socket2 = new Socket(InetAddress.getLocalHost(), port);
                                     ObjectInputStream reader2 = new ObjectInputStream(socket2.getInputStream());
                                     ObjectOutputStream writer2 = new ObjectOutputStream(socket2.getOutputStream())) {

                                    Command scriptRequest = scriptReg.getRequest();
                                    writer2.writeObject(scriptRequest);
                                    Command scriptResponse = (Command) reader2.readObject();
                                    if (scriptRequest != null && scriptRequest.getType().equals(CommandType.UPDATE_ID) && !(scriptResponse.getMessage().equals("Элемент не найден\n") || scriptResponse.getMessage().equals("У Вас нет прав на обновление этого элемента\n"))){
                                        updateId(scriptResponse.getMessage());
                                    }
                                    if(scriptRequest != null && ((scriptRequest.getType().equals(CommandType.LOGIN) && scriptResponse.getMessage().equals("Авторизация прошла успешно")) || (request.getType().equals(CommandType.REGISTER) && scriptResponse.getMessage().equals("Регистрация прошла успешно")))){
                                        User.setCurrent(scriptResponse.getUser());
                                        User.login();
                                    }
                                    System.out.println(scriptResponse.getMessage());
                                    if (scriptRequest != null && scriptRequest.getType().equals(CommandType.EXIT)) {
                                        System.exit(0);
                                    }
                                }
                            }
                            System.out.println("Введите команду или введите help для просмотра доступных команд");
                        } catch (Exception e) {
                            System.out.println("Не удалось прочитать скрипт");
                        }
                    } else {
                        writer.writeObject(request);
                        try {
                            Command response;
                            try {
                                response = (Command) reader.readObject();
                                if (request != null && request.getType().equals(CommandType.UPDATE_ID) && !(response.getMessage().equals("Элемент не найден\n") || response.getMessage().equals("У Вас нет прав на обновление этого элемента\n"))) {
                                    updateId(response.getMessage());
                                } else {
                                    if(request != null && ((request.getType().equals(CommandType.LOGIN) && response.getMessage().equals("Авторизация прошла успешно")) || (request.getType().equals(CommandType.REGISTER) && response.getMessage().equals("Регистрация прошла успешно")))){
                                        User.setCurrent(response.getUser());
                                        User.login();
                                    }
                                    else if(request != null && (request.getType().equals(CommandType.LOGOUT))){
                                        User.logout();
                                    }
                                    System.out.println(response.getMessage());
                                }
                            } catch (ClassNotFoundException e) {
                                System.out.println("Ошибка при чтении ответа сервера");
                            }
                        } catch (EOFException | NullPointerException e) {
                            System.out.println("Команда не опознана. Введите help, чтобы вывести справку по доступным командам");
                        }
                        if (request != null && request.getType().equals(CommandType.EXIT)) {
                            System.exit(0);
                        } else {
                            System.out.println("\nВведите команду или введите help для просмотра доступных команд");
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка при подключении..");
                e.printStackTrace();
            }
        }
    }
    private static void updateId(String id) throws ClassNotFoundException {
        try (Socket upSocket = new Socket(InetAddress.getLocalHost(), port);
             ObjectInputStream upReader = new ObjectInputStream(upSocket.getInputStream());
             ObjectOutputStream upWriter = new ObjectOutputStream(upSocket.getOutputStream())) {

            Command updateRequest = new ElementCommand(CommandType.valueOf("UPDATE_TASK"), NewElement.create(), id);
            upWriter.writeObject(updateRequest);

            Command updateResponse = (Command)upReader.readObject();
            System.out.println(updateResponse.getMessage());
            System.out.println("\nВведите команду или введите help для просмотра доступных команд");
        }
        catch(IOException e){
            System.out.println("Не удалось обновить идентификатор");
        }
    }
}