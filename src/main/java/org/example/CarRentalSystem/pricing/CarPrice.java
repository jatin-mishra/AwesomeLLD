package org.example.CarRentalSystem.pricing;

import java.util.Map;

public class CarPrice {
    protected Double price;
    protected Map<String, Object> metadata;

    public CarPrice(Double price, Map<String, Object> metadata) {
        this.price = price;
        this.metadata = metadata;
    }
}
