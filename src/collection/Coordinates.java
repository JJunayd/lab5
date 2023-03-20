package collection;
import productValidation.InvalidFieldHandler;

public class Coordinates {
    private Float x; //Максимальное значение поля: 570, Поле не может быть null
    private Integer y; //Поле не может быть null
    public Float getX(){
        return this.x;
    }
    public Coordinates(float x, int y){
        setX(x);
        setY(y);
    }
    public Integer getY() {
        return y;
    }

    public void setX(Float x) {
        if(x == null){
            InvalidFieldHandler.printMessage("Значение x класса Coordinates не может быть null");
        }
        else if(x > 570){
            InvalidFieldHandler.printMessage("Значение x класса Coordinates не может превышать 570");
        }
        else{this.x = x;}
    }

    public void setY(Integer y) {
        if(y == null){
            InvalidFieldHandler.printMessage("Значение y класса Coordinates не может быть null");
        }
        else{this.y = y;}
    }
    @Override
    public String toString(){
        return "{\"x\":" + this.getX() + ",\"y\":" + this.getY() + "}";
    }
}
