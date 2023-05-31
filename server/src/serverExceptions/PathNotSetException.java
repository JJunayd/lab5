/**
 * Исключение неустановленного пути к файлу (используется при установке переменных окружения)
 */
package serverExceptions;

public class PathNotSetException extends Exception{
    public PathNotSetException(String message){
        super(message);
    }
}
