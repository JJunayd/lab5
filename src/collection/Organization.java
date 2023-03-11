package collection;

import java.util.Random;
public class Organization {
    private Long orgId; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String orgName; //Поле не может быть null, Строка не может быть пустой
    private long employeesCount; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
    private Address officialAddress; //Поле может быть null

    public Organization() {
        this.updateId();
    }

    public void updateId() {
        Random random = new Random();
        this.orgId = random.nextLong(Long.MAX_VALUE) + 1;
    }

    public Long getId() {
        return orgId;
    }
    public void setOrgId(long orgId){
        this.orgId = orgId;
    }

    public String getName() {
        return orgName;
    }

    public void setName(String name) {
        this.orgName = name;
    }

    public long getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(long employeesCount) {
        this.employeesCount = employeesCount;
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
        this.officialAddress = officialAddress;
    }

    @Override
    public String toString() {
        return "name: " + this.getName() + ", employeesCount: " + this.getEmployeesCount()
                + ",type: " + this.getType() + ",officialAdress:" + this.getOfficialAddress().toString();
    }
}
