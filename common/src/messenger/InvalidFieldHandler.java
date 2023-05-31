package messenger;

import exceptions.InvalidFieldException;

/**
 * Класс, выводящий информацию о выброшенном исключении, связанном с нарушением контрактов классов коллекции
 */


public class InvalidFieldHandler {
    public static void printMessage(String message){
        try {
            throw new InvalidFieldException(message);
        } catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
        }
    }
}
