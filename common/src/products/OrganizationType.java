/**
 * Тип организации (public, government, trust, private limited company, open joint company)
 */
package products;

import java.io.Serializable;

public enum OrganizationType implements Serializable {
    PUBLIC("public"),
    GOVERNMENT("government"),
    TRUST("trust"),
    PRIVATE_LIMITED_COMPANY("private_limited_company"),
    OPEN_JOINT_STOCK_COMPANY("open_joint_stock_company");
    private final String name;

    OrganizationType(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
