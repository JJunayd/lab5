package authorization;

import commands.CommandType;
import commands.StringArgCommand;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class User {
    private static final Scanner console = new Scanner(System.in);
    private static String getUserName(){
        System.out.println("Введите имя пользователя: ");
        String userName = console.nextLine().trim();
        return userName;
    }
    private static String getEncPass() throws NoSuchAlgorithmException{
        System.out.println("Введите пароль: ");
        String pass = console.nextLine();
        String encPass = authorization.Sha1Hasher.encrypt(pass);
        return encPass;
    }
    public static StringArgCommand loginCreds(){
        try {
            return new StringArgCommand(CommandType.LOGIN, getUserName(), getEncPass());
        }
        catch (NoSuchAlgorithmException e){
            System.out.println("Ошибка при зашифровке пароля");
            return new StringArgCommand(CommandType.LOGIN, getUserName(), null);
        }
    }
    public static StringArgCommand regCreds(){
        try{
        return new StringArgCommand(CommandType.REGISTER, getUserName(), getEncPass());
        }
        catch (NoSuchAlgorithmException e){
            System.out.println("Ошибка при зашифровке пароля");
            return new StringArgCommand(CommandType.REGISTER, getUserName(), null);
        }
    }
    private static String currentUser;
    public static void setCurrent(String username){
        currentUser = username;
    }
    public static String current(){
        return currentUser;
    }
    public static boolean authorized;
    public static void login(){
        authorized = true;
    }
    public static void logout(){
        authorized = false;
        setCurrent(null);
    }
}
