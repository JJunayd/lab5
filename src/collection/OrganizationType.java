/**
 * Тип организации (public, government, trust, private limited company, open joint company)
 */
package collection;

public enum OrganizationType {
    PUBLIC("public"),
    GOVERNMENT("government"),
    TRUST("trust"),
    PRIVATE_LIMITED_COMPANY("private limited company"),
    OPEN_JOINT_STOCK_COMPANY("open joint stock company");
    private final String name;

    OrganizationType(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
