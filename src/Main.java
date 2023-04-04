/**
 * Класс, осуществляющий запуск программы
 */
import commands.registry.InputCommandRegister;

public class Main {
    public static void main(String[] args) {
        ProductLoader.load();
        InputCommandRegister inputCommandRegister = new InputCommandRegister();
        inputCommandRegister.run();
    }
}