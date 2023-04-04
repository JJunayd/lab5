/**
 * Продукт
 * Значение поля id должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
 * Поле name не может быть null, Строка не может быть пустой
 * Поле coordinates не может быть null
 * Поле creationDate не может быть null, Значение этого поля должно генерироваться автоматически
 * Значение поля price должно быть больше 0
 * Поле unitOfMeasure может быть null
 * Поле manufacturer может быть null
 */
package collection;
import com.google.gson.*;
import parser.ProductConverter;
import productValidation.InvalidFieldHandler;
import java.time.ZonedDateTime;
public class Product implements Comparable<Product> {
    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long price; //Значение поля должно быть больше 0
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Organization manufacturer; //Поле может быть null

    public boolean isValid = true;
    public Product(String name, Coordinates coordinates, ZonedDateTime creationDate, long price, UnitOfMeasure unitOfMeasure, Organization manufacturer){
        updateId();
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(creationDate);
        setPrice(price);
        setUnitOfMeasure(unitOfMeasure);
        setManufacturer(manufacturer);
    }
    public void updateId() {
        if(this.id == null){
            this.setId(1);
        }
        else {
            setId(this.id + 1);
        }
    }
    public void updateOrgId(){
        this.getManufacturer().updateId();
    }
    @Override
    public int compareTo(Product another) {
        if (this.id > another.id) {
            return 1;
        }
        else{
            return -1;
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
        if(id <= 0){
            InvalidFieldHandler.printMessage("Значение id должно быть больше нуля");
            isValid = false;
        }
        else{this.id = id;}
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name) {
        if(name == null || name.equals("")){
            InvalidFieldHandler.printMessage("Значение name не может быть пустым(null)");
            isValid = false;
        }
        else{this.name = name;}
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if(coordinates == null){
            InvalidFieldHandler.printMessage("Значение coordinates не может быть null");
            isValid = false;
        }
        else{this.coordinates = coordinates;}
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        if(creationDate == null){
            InvalidFieldHandler.printMessage("Значение creationDate не может быть null");
            isValid = false;
        }
        else{this.creationDate = creationDate;}
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        if(price <= 0){
            InvalidFieldHandler.printMessage("Значение price должно быть больше нуля");
            isValid = false;
        }
        else{this.price = price;}
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