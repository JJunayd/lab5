package multithreading;

import commands.Command;
import commands.CommandType;
import commands.SaveCommand;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class RespSendThread {
    public RespSendThread(ObjectOutputStream writer, Command request, Command response){
    this.writer = writer;
    this.request = request;
    this.response = response;
}
    private ObjectOutputStream writer;
    private Command request;
    private Command response;
    private Thread t = new Thread() {
        @Override
        public void run() {
            if (request != null && request.getType().equals(CommandType.EXIT)) {
                response.setMessage(response.getMessage() + "\n" + new SaveCommand().execute().getMessage());
                send();
            } else {
                send();
            }
        }
    };
    public void start() {
        t.start();
    }

    public void join() throws InterruptedException {
        t.join();
    }

    private void send(){
        try {
            this.writer.writeObject(this.response);
        } catch (IOException e) {
            System.out.println("Ошибка при отправке ответа");
        }
    }
}
