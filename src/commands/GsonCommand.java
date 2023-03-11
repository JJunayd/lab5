package commands;

import collection.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import parser.ProductConverter;

public abstract class GsonCommand {
    String jsonProduct;

    private final GsonBuilder builder = new GsonBuilder();

    {builder.registerTypeAdapter(Product.class, new ProductConverter());}

    Gson gson = builder.create();

    public Product fromJson(String jsonProduct){
        Product product;
        product = gson.fromJson(jsonProduct, Product.class);
        return product;
    }
    public void setJsonProduct(String jsonProduct) {
        this.jsonProduct = jsonProduct;
    }
}
