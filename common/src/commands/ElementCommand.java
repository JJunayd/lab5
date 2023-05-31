package commands;

import products.Product;

public class ElementCommand extends Command{
    Product element;
    public ElementCommand(CommandType type, Product arg){
        super(type);
        this.element = arg;
    }
    ElementCommand(){}
    public void setElement(Product arg){
        this.element = arg;
    }
    public Product getElement(){
        return this.element;
    }
}
