package collection;
import com.google.gson.*;
import parser.ProductConverter;
import productValidation.InvalidFieldHandler;

import java.time.ZonedDateTime;
import java.util.Random;
public class Product implements Comparable<Product> {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long price; //Значение поля должно быть больше 0
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Organization manufacturer; //Поле может быть null

    public Product(){
        updateId();
    }
    public void updateId() {
        Random random = new Random();
        this.id = random.nextLong(Long.MAX_VALUE) + 1;
    }
    public void updateOrgId(){
        this.getManufacturer().updateId();
    }
    @Override
    public int compareTo(Product another) {
        while(this.id == another.id){
            this.updateId(); //на всякий случай..
        }
        if (this.id > another.id) {
            return 1;
        }
        else{
            return -1;
        }
    }
    public boolean isValid() {
        if(this.getId() <= 0){
            InvalidFieldHandler.printMessage("Значение id должно быть больше нуля");
            return false;
        }
        if(this.getName() == null || this.getName().equals("")){
            InvalidFieldHandler.printMessage("Значение name не может быть пустым(null)");
            return false;
        }
        if(this.getCoordinates() == null){
            InvalidFieldHandler.printMessage("Значение coordinates не может быть null");
            return false;
        }
        if(this.getCreationDate() == null){
            InvalidFieldHandler.printMessage("Значение creationDate не может быть null");
            return false;
        }
        if(this.getPrice() <= 0){
            InvalidFieldHandler.printMessage("Значение price должно быть больше нуля");
            return false;
        }
        if(this.getManufacturer() == null && this.getManufacturer().getId() <= 0){
            InvalidFieldHandler.printMessage("Значение id класса Organisation должно быть больше нуля");
            return false;
        }
        if(this.getManufacturer().getName() == null || this.getManufacturer().getName().equals("")){
            InvalidFieldHandler.printMessage("Значение name класса Organisation не может быть равно пустым(null)");
            return false;
        }
        if(this.getManufacturer().getOfficialAddress().getStreet() == null){
            InvalidFieldHandler.printMessage("Значение street класса OfficialAddress не может быть null");
            return false;
        }
        if(this.getManufacturer().getOfficialAddress().getZipCode() == null){
            InvalidFieldHandler.printMessage("Значение zipCode класса OfficialAddress не может быть null");
            return false;
        }
        if(this.getManufacturer().getOfficialAddress().getZipCode().length() > 18){
            InvalidFieldHandler.printMessage("Значение zipCode класса OfficialAddress не может превышать 18 символов");
            return false;
        }
        if(this.getManufacturer().getType() == null || this.getUnitOfMeasure() == null){
            return false;
        }
        else{
            return true;
        }
    }
    @Override
    public String toString() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Product.class, new ProductConverter());
        Gson gson = builder.create();
        return gson.toJson(this);
    }
    public long getId(){
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
    public UnitOfMeasure getUnitOfMeasure() {
        return this.unitOfMeasure;
    }
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
    public Organization getManufacturer() {
        return this.manufacturer;
    }
    public void setManufacturer(Organization manufacturer) {
        this.manufacturer = manufacturer;
    }
}