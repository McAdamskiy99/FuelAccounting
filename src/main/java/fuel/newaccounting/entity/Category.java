package fuel.newaccounting.entity;

import javax.xml.catalog.CatalogResolver;

public enum Category {
    A,
    B,
    C,
    D;

    public static boolean exists(String value) {
        if (value == null) return true;
        for (Category category : values()) {
            if (category.name().equals(value)) {
                return false;
            }
        }
        return true;
    }
}
