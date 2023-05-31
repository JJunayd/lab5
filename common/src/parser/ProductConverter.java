/**
 * Класс, преобразующий элементы коллекции в объекты JsonObject и наоборот
 */
package parser;

import products.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;

public class ProductConverter implements JsonSerializer<Product>, JsonDeserializer<Product>  {
    public JsonElement serialize(Product product, Type type,
                                 JsonSerializationContext context) {
        JsonObject jsonProduct = new JsonObject();
        jsonProduct.addProperty("id", product.getId());
        jsonProduct.addProperty("name", product.getName());
        jsonProduct.addProperty("creationTime", product.getCreationDate().toString());
        jsonProduct.addProperty("price", product.getPrice());
        try {
            jsonProduct.addProperty("unitOfMeasure", product.getUnitOfMeasure().toString());
        }
        catch(NullPointerException e){
            jsonProduct.addProperty("unitOfMeasure", "");
        }

        jsonProduct.addProperty("x", product.getCoordinates().getX());
        jsonProduct.addProperty("y", product.getCoordinates().getY());

        jsonProduct.addProperty("orgId", product.getManufacturer().getId());
        jsonProduct.addProperty("orgName", product.getManufacturer().getName());
        jsonProduct.addProperty("employeesCount", product.getManufacturer().getEmployeesCount());
        try {
            jsonProduct.addProperty("type", product.getManufacturer().getType().toString());
        }
        catch(NullPointerException e){
            jsonProduct.addProperty("type", "");
        }
        jsonProduct.addProperty("street", product.getManufacturer().getOfficialAddress().getStreet());
        jsonProduct.addProperty("zipCode", product.getManufacturer().getOfficialAddress().getZipCode());
        return jsonProduct;
    }
    public String getAsOptString(JsonObject object, String memberName){
        if(object.get(memberName) == null){
            return null;
        }
        else {
            return object.get(memberName).getAsString();
        }
    }
    public Long getAsOptLong(JsonObject object, String memberName){
        if(object.get(memberName) == null){
            return null;
        }
        else{return object.get(memberName).getAsLong();}
    }
    public Float getAsOptFloat(JsonObject object, String memberName){

        if(object.get(memberName) == null){
            return null;
        }
        else {
            return object.get(memberName).getAsFloat();
        }
    }
    public Integer getAsOptInt(JsonObject object, String memberName){
        if(object.get(memberName) == null){
            return null;
        }
        else{return object.get(memberName).getAsInt();}
    }

    public Product deserialize(JsonElement json, Type typeOfT,
                              JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        try {
            String street = getAsOptString(object, "street");
            String zipCode = getAsOptString(object, "zipCode");

            Address addr = new Address(street, zipCode);

            String orgName = getAsOptString(object, "orgName");
            long employeesCount = getAsOptLong(object, "employeesCount");
            OrganizationType type = null;
            try {
                type = OrganizationType.valueOf(object.get("type").getAsString().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректное значение type");
            }
            Organization man = new Organization(orgName, employeesCount, type, addr);
            if (object.get("orgId") != null) {
                long orgId = object.get("orgId").getAsLong();
                man.setOrgId(orgId);
            }

            float x = getAsOptFloat(object, "x");
            int y = getAsOptInt(object, "y");

            Coordinates coord = new Coordinates(x, y);

            String name = getAsOptString(object, "name");

            ZonedDateTime creationDate;
            try {
                creationDate = ZonedDateTime.parse(object.get("creationTime").getAsString());
            } catch (NullPointerException e) {
                creationDate = ZonedDateTime.now();
            }
            long price = getAsOptLong(object, "price");
            UnitOfMeasure unitOfMeasure = null;
            try {
                unitOfMeasure = UnitOfMeasure.valueOf(getAsOptString(object, "unitOfMeasure").toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректное значение unitOfMeasure");
            } catch (NullPointerException e) { //потому что может быть null
            }
            Product product = new Product(name, coord, creationDate, price, unitOfMeasure, man);

            if (object.get("id") != null) {
                long id = object.get("id").getAsLong();
                product.setId(id);
            }
            if (product.isValid) {
                return product;
            }
            else{
                System.out.println("Нарушен контракт. Элемент не добавлен в коллекцию");
                return null;
            }
        }
        catch(RuntimeException e){
            System.out.println("Ошибка в описании продукта. Элемент не добавлен в коллекцию");
            return null;
        }
    }
}