package collection;

public class Address {
    private String street; //Поле не может быть null
    private String zipCode; //Длина строки не должна быть больше 18, Поле не может быть null
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString(){
        return "street:" + this.getStreet() + ",zipCode:" + this.getZipCode();
    }
}
