/**
 * Дочерний класс CommandRegister, читающий команды из командной строки
 */
package commands.registry;

import java.util.Scanner;

public class InputCommandRegister extends CommandRegister {
    {
        scanner = new Scanner(System.in);
    }
}
