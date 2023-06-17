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
package products;
import com.google.gson.*;
import messenger.InvalidFieldHandler;
import parser.ProductConverter;

import java.io.Serializable;

import java.util.Date;

public class Product implements Comparable<Product>, Serializable {
    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long price; //Значение поля должно быть больше 0
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private Organization manufacturer; //Поле не может быть null
    private String creator;

    public boolean isValid = true;
    public Product(Long id, String name, Coordinates coordinates, Date creationDate, long price, UnitOfMeasure unitOfMeasure, Organization manufacturer, String creator){
        setId(id);
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(creationDate);
        setPrice(price);
        setUnitOfMeasure(unitOfMeasure);
        setManufacturer(manufacturer);
        setCreator(creator);
    }
    public Product(String name, Coordinates coordinates, long price, UnitOfMeasure unitOfMeasure, Organization manufacturer){
        setName(name);
        setCoordinates(coordinates);
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
    public int compareTo(Product another) {
        return Long.compare(this.getPrice(), another.getPrice());
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
        else if(!coordinates.isValid){
            InvalidFieldHandler.printMessage("Нарушен контракт класса Coordinates");
            isValid = false;
        }
        else{this.coordinates = coordinates;}
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
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
        if(!manufacturer.isValid){
            isValid = false;
        }
        else {
            this.manufacturer = manufacturer;
        }
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Product product = (Product) obj;
            return this.getName().equals(product.getName()) && this.getManufacturer().getOfficialAddress().getStreet().equals(product.getManufacturer().getOfficialAddress().getStreet()) && this.getManufacturer().getOfficialAddress().getZipCode().equals(product.getManufacturer().getOfficialAddress().getZipCode()) && this.getManufacturer().getType().equals(product.getManufacturer().getType()) && this.getManufacturer().getEmployeesCount() == product.getManufacturer().getEmployeesCount() && this.getManufacturer().getName().equals(product.getManufacturer().getName()) && this.getCoordinates().getX().equals(product.getCoordinates().getX()) && this.getCoordinates().getY().equals(product.getCoordinates().getY()) && this.getUnitOfMeasure().equals(product.getUnitOfMeasure()) && this.getCreator().equals(product.getCreator());
        }
        catch(ClassCastException e){
            return false;
        }
    }


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}