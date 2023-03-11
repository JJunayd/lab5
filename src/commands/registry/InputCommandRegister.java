package commands.registry;

import commands.*;

import java.util.Scanner;

public class InputCommandRegister extends CommandRegister {
    public InputCommandRegister(CommandExecuter comEx) {
        super(comEx);
    }
    {
        scanner = new Scanner(System.in);
    }
}
