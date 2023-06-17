package multithreading;

import commands.Command;
import registry.InputCommandRegister;

import java.util.concurrent.Callable;

public class HandleReq {
    public HandleReq(Command request){
        this.request = request;
    }
    private Callable<Command> callable = new Callable<>() {
        @Override
        public Command call() {
            return inputCommandRegister.handleCommand(request);
        }
    };
    public Callable<Command> getCallable(){
        return this.callable;
    }
    private Command request;
    private final InputCommandRegister inputCommandRegister = new InputCommandRegister();
}
