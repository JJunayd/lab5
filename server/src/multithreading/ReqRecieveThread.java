package multithreading;

import commands.Command;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReqRecieveThread {
    public ReqRecieveThread(ObjectInputStream reader){
        this.reader = reader;
    }
    private ObjectInputStream reader;

    private Thread t = new Thread() {
        @Override
        public void run() {
            try {
                setRequest((Command)reader.readObject());
            } catch (IOException e) {
                System.out.println("Ошибка при чтении запроса");
            } catch (ClassNotFoundException e) {
                System.out.println("Некорректный формат данных");
            }
        }
    };
    private Command request;
    public void start() {
        t.start();
    }

    public void join() throws InterruptedException {
        t.join();
    }

    public Command getRequest() {
        return request;
    }

    public void setRequest(Command request) {
        this.request = request;
    }
}
