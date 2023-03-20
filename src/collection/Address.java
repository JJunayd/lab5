package collection;

import productValidation.InvalidFieldHandler;

public class Address {
    private String street; //Поле не может быть null
    private String zipCode; //Длина строки не должна быть больше 18, Поле не может быть null
    public Address(String street, String zipCode){
        setStreet(street);
        setZipCode(zipCode);
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if(street == null){
            InvalidFieldHandler.printMessage("Значение street класса OfficialAddress не может быть null");
        }
        else{this.street = street;}
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        if(zipCode == null) {
            InvalidFieldHandler.printMessage("Значение zipCode класса OfficialAddress не может быть null");
        }
        else if(zipCode.length() > 18){
            InvalidFieldHandler.printMessage("Значение zipCode класса OfficialAddress не может превышать 18 символов");
        }
        else{this.zipCode = zipCode;}
    }

    @Override
    public String toString(){
        return "street:" + this.getStreet() + ",zipCode:" + this.getZipCode();
    }
}
