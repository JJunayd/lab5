/**
 * Координаты
 * Максимальное значение поля х: 570, Поле не может быть null
 * Поле y не может быть null
 */
package products;

import messenger.InvalidFieldHandler;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private long id;
    private Float x; //Максимальное значение поля: 570, Поле не может быть null
    private Integer y; //Поле не может быть null
    public boolean isValid = true;
    public Float getX(){
        return this.x;
    }
    public Coordinates(long id, float x, int y){
        setId(id);
        setX(x);
        setY(y);
    }
    public Coordinates(float x, int y){
        setX(x);
        setY(y);
    }
    public void setId(long id){
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }
    public Integer getY() {
        return y;
    }

    public void setX(Float x) {
        if(x == null){
            InvalidFieldHandler.printMessage("Значение x класса Coordinates не может быть null");
            isValid = false;
        }
        else if(x > 570){
            InvalidFieldHandler.printMessage("Значение x класса Coordinates не может превышать 570");
            isValid = false;
        }
        else{this.x = x;}
    }

    public void setY(Integer y) {
        if(y == null){
            InvalidFieldHandler.printMessage("Значение y класса Coordinates не может быть null");
            isValid = false;
        }
        else{this.y = y;}
    }
    @Override
    public String toString(){
        return "{\"x\":" + this.getX() + ",\"y\":" + this.getY() + "}";
    }
}
