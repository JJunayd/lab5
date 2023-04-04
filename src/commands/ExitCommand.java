/**
 * Команда, завершающая работу программы
 */
package commands;

public class ExitCommand implements NoArgCommand {

    @Override
    public void execute() {
        System.exit(0);
    }
}
