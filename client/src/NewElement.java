/**
 * Абстрактный класс для команд, создающих новые элементы
 */


import authorization.User;
import products.*;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class NewElement {
    private static final Scanner productInput = new Scanner(System.in);
    private static <T> T inputString(Class<T> dataType, String message) {
        while (true) {
            System.out.println(message);
            try {
                String argument = productInput.nextLine().trim();
                try {
                    if(argument.equals("")){
                        throw new RuntimeException("Поле не может быть пустым");
                    }
                    if (dataType == String.class) {
                        if(message.equals("Введите зипкод: ") && argument.length()>18){
                            throw new RuntimeException("Длина поля не должна превышать 18 символов");
                        }
                        else {
                            return (T) Objects.requireNonNull(argument);
                        }
                    } else if (dataType == Double.class) {
                        return dataType.cast(Double.parseDouble(Objects.requireNonNull(argument)));
                    } else if (dataType == Integer.class) {
                        return dataType.cast(Integer.parseInt(Objects.requireNonNull(argument)));
                    } else if (dataType == Long.class) {
                        T result = dataType.cast(Long.parseLong(Objects.requireNonNull(argument)));
                        if((long)result>0) {
                            return result;
                        }
                        else{throw new NumberFormatException("Значение поля должно быть больше нуля");}
                    } else if (dataType == Float.class) {
                        T result = dataType.cast(Float.parseFloat(Objects.requireNonNull(argument.replace(",", "."))));
                        if((float)result<=570) {
                            return result;
                        }
                        else{throw new RuntimeException("Значение поля не может превышать 570");}
                    }
                } catch (RuntimeException e) {
                    System.out.println("Поле введено некорректно");
                    System.out.println(e.getMessage());
                }
            }
            catch(NoSuchElementException e){
                System.exit(0);
            }
        }
    }
    private static <T extends Enum<T>> T inputEnum(Class<T> enumName, String message) {
        while (true) {
            System.out.println(message);
            var value = productInput.nextLine().trim();
            try {
                if(value.matches("-?\\d+(\\.\\d+)?")) {
                    int id = Integer.parseInt(value) - 1;
                    T[] possibleValues = enumName.getEnumConstants();
                    if (id >= 0 && id < possibleValues.length) {
                        return possibleValues[id];
                    }
                    else{
                        throw new IllegalArgumentException();
                    }
                }
                else {
                    return Enum.valueOf(enumName, value.toUpperCase());
                }
            } catch (RuntimeException e) {
                System.out.println("Поле введено некорректно");
            }
        }
    }

    public static Product create() {
        String name = inputString(String.class, "Введите наименование продукта: ");
        Float x = inputString(Float.class, "Введите ось x координат продукта: ");
        Integer y = inputString(Integer.class, "Введите ось y координат продукта: ");
        Long price = inputString(Long.class, "Введите цену продукта: ");
        UnitOfMeasure unitOfMeasure = inputEnum(UnitOfMeasure.class, "Введите единицу измерения продукта(CENTIMETERS, PCS, LITERS, MILLILITERS, GRAMS): ");
        String orgName = inputString(String.class, "Введите название производителя: ");
        Long employeesCount = inputString(Long.class, "Введите число сотрудников: ");
        OrganizationType type = inputEnum(OrganizationType.class, "Введите тип организации(PUBLIC, GOVERNMENT, TRUST, PRIVATE_LIMITED_COMPANY, OPEN_JOINT_STOCK_COMPANY): ");
        String street = inputString(String.class, "Введите улицу: ");
        String zipCode = inputString(String.class, "Введите зипкод: ");
        Address address = new Address(street, zipCode);
        Organization man = new Organization(orgName, employeesCount, type, address);
        Coordinates coord = new Coordinates(x, y);
        Product product = new Product(name, coord, price, unitOfMeasure, man);
        product.setCreator(User.current());
        if(product.isValid){
            return product;
        }
        else{
            return null;
        }
    }
}
