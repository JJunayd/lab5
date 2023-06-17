package commands;

import products.Product;

public class ElementCommand extends Command{
    Product element;
    String addArg;

    public void setAddArg(String addArg) {
        this.addArg = addArg;
    }

    public String getAddArg() {
        return addArg;
    }

    public ElementCommand(CommandType type, Product arg){
        super(type);
        this.element = arg;
    }
    public ElementCommand(CommandType type, Product arg, String addArg){
        super(type);
        this.element = arg;
        this.addArg = addArg;
    }
    ElementCommand(){}
    public void setElement(Product arg){
        this.element = arg;
    }
    public Product getElement(){
        return this.element;
    }
}
