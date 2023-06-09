/**
 * Организация
 * Поле orgId не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
 * Поле orgName не может быть null, Строка не может быть пустой
 * Значение поля employeesCount должно быть больше 0
 * Поле type может быть null
 * Поле может officialAddress быть null
 */
package products;

import messenger.InvalidFieldHandler;

import java.io.Serializable;

public class Organization implements Serializable {
    private Long orgId; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String orgName; //Поле не может быть null, Строка не может быть пустой
    private long employeesCount; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null
    private Address officialAddress; //Поле не может быть null
    public boolean isValid = true;

    public Organization(Long orgId, String orgName, long employeesCount, OrganizationType type, Address officialAddress) {
            setOrgId(orgId);
            setName(orgName);
            setEmployeesCount(employeesCount);
            setType(type);
            setOfficialAddress(officialAddress);
    }
    public Organization(String orgName, long employeesCount, OrganizationType type, Address officialAddress) {
        setName(orgName);
        setEmployeesCount(employeesCount);
        setType(type);
        setOfficialAddress(officialAddress);
    }

    public void updateId() {
        if(this.orgId == null){
            this.setOrgId(1);
        }
        else {
            this.setOrgId(this.orgId + 1);
        }
    }

    public Long getId() {
        return orgId;
    }
    public void setOrgId(long orgId){
        if(orgId <= 0){
            InvalidFieldHandler.printMessage("Значение id класса Organisation должно быть больше нуля");
            isValid = false;
        }
        else{this.orgId = orgId;}
    }

    public String getName() {
        return orgName;
    }

    public void setName(String name) {
        if(name == null || name.equals("")){
            InvalidFieldHandler.printMessage("Значение name класса Organisation не может быть пустым(null)");
            isValid = false;
        }
        else{this.orgName = name;}
    }

    public long getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(long employeesCount) {
        if(employeesCount <= 0){
            InvalidFieldHandler.printMessage("Значение employeesCount класса Organization должно быть больше нуля");
            isValid = false;
        }
        else{this.employeesCount = employeesCount;}
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    public void setOfficialAddress(Address officialAddress) {
        if(!officialAddress.isValid){
            isValid = false;
        }
        else {
            this.officialAddress = officialAddress;
        }
    }

    @Override
    public String toString() {
        return "name: " + this.getName() + ", employeesCount: " + this.getEmployeesCount()
                + ",type: " + this.getType() + ",officialAdress:" + this.getOfficialAddress().toString();
    }
}
