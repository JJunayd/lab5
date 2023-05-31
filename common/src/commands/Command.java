package commands;

import products.Product;

import java.io.Serializable;

public abstract class Command implements Serializable {
    protected static String scriptNotice = "СКРИПТ";
    CommandType type;
    public Command(CommandType type){
        this.type = type;
    }

    Command() {}

    public CommandType getType(){
        return this.type;
    }
    public Command execute(){
        return null;
    }
    private String message;
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message = message;
    }
}
