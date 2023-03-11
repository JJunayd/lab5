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

        Address addr = new Address();

        addr.setStreet(object.get("street").getAsString());
        addr.setZipCode(object.get("zipCode").getAsString());

        Organization man = new Organization();

        try {
            man.setOrgId(object.get("orgId").getAsLong());
        }
        catch(NullPointerException e){
            man.setOrgId(0);
        }
        man.setName(object.get("orgName").getAsString());
        man.setEmployeesCount(object.get("employeesCount").getAsLong());
        try{
            man.setType(OrganizationType.valueOf(object.get("type").getAsString()));
        }
        catch(IllegalArgumentException e){
            System.out.println("Некорректное значение type");
        }
        man.setOfficialAddress(addr);

        Coordinates coord = new Coordinates();

        coord.setX(object.get("x").getAsFloat());
        coord.setY(object.get("y").getAsInt());

        Product product = new Product();

        try {
            product.setId(object.get("id").getAsLong());
        }
        catch(NullPointerException e){
            product.setId(0);
        }
        product.setName(object.get("name").getAsString());
        try {
            product.setCreationDate(ZonedDateTime.parse(object.get("creationTime").getAsString()));
        }
        catch(NullPointerException e){
            product.setCreationDate(ZonedDateTime.now());
        }
        product.setPrice(object.get("price").getAsLong());
        try {
            product.setUnitOfMeasure(UnitOfMeasure.valueOf(object.get("unitOfMeasure").getAsString()));
        }
        catch(IllegalArgumentException e){
            System.out.println("Некорректное значение unitOfMeasure");
        }
        product.setCoordinates(coord);
        product.setManufacturer(man);

        return product;
    }
}