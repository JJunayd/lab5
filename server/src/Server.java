
import commands.*;
import manager.CollectionManager;
import manager.DatabaseManager;

import multithreading.HandleReq;
import multithreading.ReqRecieveThread;
import multithreading.RespSendThread;
import multithreading.ThreadManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class Server {
    private static int port = 9703;
    public static void main(String[] args) {
        System.out.println("НАЧАЛ РАБОТАТЬ");
        CollectionManager.clear();
        DatabaseManager.loadCollection();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream reader = new ObjectInputStream(socket.getInputStream())) {
                        System.out.println("ПОЛУЧИЛ КОМАНДУ");
                        try {
                            ReqRecieveThread recieve = new ReqRecieveThread(reader);
                            recieve.start();
                            recieve.join();
                            Command request = recieve.getRequest();
                            ExecutorService handlePool = ThreadManager.getRequestThreadPool();
                            Command response = handlePool.submit(new HandleReq(request).getCallable()).get();
                            RespSendThread respond = new RespSendThread(writer, request, response);
                            respond.start();
                            respond.join();
                        } catch (InterruptedException | ExecutionException e) {
                            System.out.println("Ошибка на сервере..");
                        }
                    } catch (IOException e) {
                        System.out.println("Ошибка при подключении к серверу..");
                    }
                }
            } catch (IOException e) {
            System.out.println("Ошибка при подключении к серверу...");
        }
    }
}

