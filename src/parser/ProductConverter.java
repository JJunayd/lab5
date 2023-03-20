package parser;

import collection.*;
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
        jsonProduct.addProperty("unitOfMeasure", product.getUnitOfMeasure().toString());

        jsonProduct.addProperty("x", product.getCoordinates().getX());
        jsonProduct.addProperty("y", product.getCoordinates().getY());

        jsonProduct.addProperty("orgId", product.getManufacturer().getId());
        jsonProduct.addProperty("orgName", product.getManufacturer().getName());
        jsonProduct.addProperty("employeesCount", product.getManufacturer().getEmployeesCount());
        jsonProduct.addProperty("type", product.getManufacturer().getType().toString());
        jsonProduct.addProperty("street", product.getManufacturer().getOfficialAddress().getStreet());
        jsonProduct.addProperty("zipCode", product.getManufacturer().getOfficialAddress().getZipCode());
        return jsonProduct;
    }

    public Product deserialize(JsonElement json, Type typeOfT,
                              JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        String street = object.get("street").getAsString();
        String zipCode = object.get("zipCode").getAsString();

        Address addr = new Address(street, zipCode);

        long orgId = object.get("orgId").getAsLong();
        String orgName = object.get("orgName").getAsString();
        long employeesCount = object.get("employeesCount").getAsLong();
        OrganizationType type = null;
        try{
            type = OrganizationType.valueOf(object.get("type").getAsString());
        }
        catch(IllegalArgumentException e){
            System.out.println("Некорректное значение type");
        }
        Organization man = new Organization(orgName, employeesCount, type, addr);
        man.setOrgId(orgId);

        float x = object.get("x").getAsFloat();
        int y = object.get("y").getAsInt();

        Coordinates coord = new Coordinates(x, y);

        long id = object.get("id").getAsLong();
        String name = object.get("name").getAsString();

        ZonedDateTime creationDate;
        try {
            creationDate = ZonedDateTime.parse(object.get("creationTime").getAsString());
        }
        catch(NullPointerException e){
            creationDate = ZonedDateTime.now();
        }
        long price = object.get("price").getAsLong();
        UnitOfMeasure unitOfMeasure = null;
        try {
            unitOfMeasure = UnitOfMeasure.valueOf(object.get("unitOfMeasure").getAsString());
        }
        catch(IllegalArgumentException e){
            System.out.println("Некорректное значение unitOfMeasure");
        }

        Product product = new Product(name, coord, creationDate, price, unitOfMeasure, man);
        product.setId(id);

        return product;
    }
}