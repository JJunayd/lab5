import commands.*;
import loader.ProductLoader;
import registry.InputCommandRegister;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        System.out.println("НАЧАЛ РАБОТАТЬ");

        ProductLoader.load();
        InputCommandRegister inputCommandRegister = new InputCommandRegister();

        try (ServerSocket serverSocket = new ServerSocket(6969)){
            while(true) {
                try (Socket socket = serverSocket.accept();
                    ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream reader = new ObjectInputStream(socket.getInputStream())){

                    System.out.println("ПОЛУЧИЛ КОМАНДУ");
                    try {
                        Command request = (Command) reader.readObject();

                        Command response = inputCommandRegister.handleCommand(request);
                        if (request.getType().equals(CommandType.EXIT)) {
                            response.setMessage(response.getMessage() + "\n" + new SaveCommand().execute().getMessage());
                            writer.writeObject(response);
                        } else {
                            writer.writeObject(response);
                        }
                    }
                    catch(EOFException e){
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка на сервере..");
                }
            }
        }
        catch(IOException e){
            System.out.println("Ошибка при подключении к серверу..");
        }
    }
}
