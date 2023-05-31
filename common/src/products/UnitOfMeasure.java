/**
 * Единица измерения (centimeters, pcs, liters, milliliters, grams)
 */
package products;

import java.io.Serializable;

public enum UnitOfMeasure implements Serializable {
    CENTIMETERS("centimeters"),
    PCS("pcs"),
    LITERS("liters"),
    MILLILITERS("milliliters"),
    GRAMS("grams");
    private final String name;

    UnitOfMeasure(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
