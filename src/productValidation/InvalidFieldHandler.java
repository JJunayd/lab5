package productValidation;

import exceptions.InvalidFieldException;

public class InvalidFieldHandler {
    public static void printMessage(String message){
        try {
            throw new InvalidFieldException(message);
        } catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
        }
    }
}
