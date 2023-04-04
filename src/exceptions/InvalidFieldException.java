/**
 * Исключение нарушения контрактов классов коллекции
 */
package exceptions;

public class InvalidFieldException extends Exception{
    public InvalidFieldException(String message){
        super(message);
    }

}
