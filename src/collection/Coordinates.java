package collection;

public class Coordinates {
    private Float x; //Максимальное значение поля: 570, Поле не может быть null
    private Integer y; //Поле не может быть null
    public Coordinates(){}
    public Float getX(){
        return this.x;
    }
    public Integer getY() {
        return y;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }
    @Override
    public String toString(){
        return "{\"x\":" + this.getX() + ",\"y\":" + this.getY() + "}";
    }
}
