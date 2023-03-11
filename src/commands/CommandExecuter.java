package commands;

import collection.CollectionManager;
import collection.Product;
import com.google.gson.Gson;
import commands.registry.ScriptRegister;
import exceptions.ScriptRecursionException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CommandExecuter {
    public boolean recExcThrown(String fileName) {
        boolean thrown = false;
        try (Reader reader = new InputStreamReader(new FileInputStream(fileName), Charset.defaultCharset())) {
            Path file = Path.of(fileName);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine().trim();
                if (Pattern.matches("execute_script .+", command)) {
                    String fileName1 = command.replace("execute_script ", "");
                    try (Reader reader1 = new InputStreamReader((new FileInputStream(fileName1)), Charset.defaultCharset())) {
                        Scanner scanner1 = new Scanner(reader1);
                        while (scanner1.hasNextLine()) {
                            String command1 = scanner1.nextLine().trim();
                            if (Pattern.matches("execute_script .+", command1)) {
                                Path file1 = Path.of(command1.replace("execute_script ", ""));
                                if (Files.mismatch(file, file1) == -1) {
                                    thrown = true;
                                    break;
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (thrown) {
                        break;
                    }
                }
            }
            scanner.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
            return thrown;
    }
    public void add(Product product) {
        if (product.isValid()) {
            CollectionManager.add(product);
        }
    }

    public void addIfMin(Product product) {
        if (product.isValid()) {
            if (product.compareTo(CollectionManager.head()) < 0) {
                CollectionManager.add(product);
            }
        }
    }

    public void clear() {
        CollectionManager.clear();
    }

    public void exScript(String fileName) {
        if (recExcThrown(fileName)) {
            try {
                throw new ScriptRecursionException("рекурсия..");
            } catch (ScriptRecursionException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try (Reader reader = new InputStreamReader(new FileInputStream(fileName), Charset.defaultCharset())) {
                Scanner scanner = new Scanner(reader);
                ScriptRegister scriptRegister = new ScriptRegister(this);
                scriptRegister.setScanner(scanner);
                scriptRegister.run();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public void exit() {
        System.exit(0);
    }
    public void filterGreaterThanPrice(long price) {
        ArrayList<Product> greaterPrice = new ArrayList<>();
        Iterator<Product> iter = CollectionManager.prodIter();

        while (iter.hasNext()) {
            if (iter.next().getPrice() > price) {
                greaterPrice.add(iter.next());
            }
        }
        if (greaterPrice.isEmpty()) {
            System.out.println("В коллекции нет элементов с ценой, больше данной");
        } else {
            for (int i = 0; i < greaterPrice.size(); i++) {
                System.out.print(greaterPrice.get(i));
                if (i != greaterPrice.size() - 1) {
                    System.out.println(", ");
                }
            }
        }
        greaterPrice.clear();
    }
    public void head() {
        System.out.println(CollectionManager.head());
    }
    public void help() {
        System.out.print("""
                help : вывести справку по доступным командам
                info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                add {element} : добавить новый элемент в коллекцию
                update id {element} : обновить значение элемента коллекции, id которого равен заданному
                remove_by_id id : удалить элемент из коллекции по его id
                clear : очистить коллекцию
                save : сохранить коллекцию в файл
                execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                exit : завершить программу (без сохранения в файл)
                head : вывести первый элемент коллекции
                add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
                remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный
                sum_of_price : вывести сумму значений поля price для всех элементов коллекции
                filter_greater_than_price price : вывести элементы, значение поля price которых больше заданного
                print_descending : вывести элементы коллекции в порядке убывания
                """);
    }
    public void info() {
        System.out.println("Тип коллекции: " + CollectionManager.getCollectionType());
        System.out.println("Дата инициализации: " + CollectionManager.getCreationTime());
        System.out.println("Число элементов коллекции: " + CollectionManager.getCollectionSize());
    }
    public void printDescending() {
        Object[] arr = CollectionManager.toArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i]);
            if (i != 0) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    public void removeById(long id) {
        Iterator<Product> iter = CollectionManager.prodIter();
        while (iter.hasNext()) {
            Product product = iter.next();
            if (product.getId() == id) {
                CollectionManager.remove(product);
            }
        }
    }
    public void removeLower(Product product) {
        if(product.isValid()){
            ArrayList<Product> temp = new ArrayList<>();
            Iterator<Product> iter = CollectionManager.prodIter();
            while (iter.hasNext()) {
                if (iter.next().toString().equals(product.toString())) {
                    temp.add(product);
                    while (CollectionManager.head().getId() != product.getId()){
                        CollectionManager.collection.poll();
                    }
                }
            }
            if (temp.isEmpty()) {
                System.out.println("Элемент не найден");
            }
            temp.clear();
        }
    }
    public void save(String saveToPath, Gson gson) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveToPath))) {
            Object[] arr = CollectionManager.toArray();
            if(arr.length == 0){
                writer.write("");
            }
            else if (arr.length == 1) {
                writer.write(gson.toJson(arr[0]));
            } else {
                writer.write(gson.toJson(arr));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void show() {
        System.out.println(CollectionManager.show().toString());
    }
    public void sumOfPrice() {
        long sum_of_price = 0;
        Iterator<Product> iter = CollectionManager.prodIter();
        while (iter.hasNext()) {
            sum_of_price += iter.next().getPrice();
        }
        System.out.println("Сумма цен всех продуктов коллекции: " + sum_of_price);
    }
    public void updateId(Product product) {
        if(product.isValid()) {
            Iterator<Product> iter = CollectionManager.prodIter();
            while (iter.hasNext()) {
                Product nextProduct = iter.next();
                while (nextProduct.getId() == product.getId()) {
                    nextProduct.updateId();
                }
            }
        }
    }
}
