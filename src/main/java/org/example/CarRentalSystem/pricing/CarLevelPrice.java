package org.example.CarRentalSystem.pricing;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CarLevelPrice extends CarPrice  {
    private String carId;
    private PriceType type;

    public CarLevelPrice(String carId, PriceType type, Double price) {
        super(price, new ConcurrentHashMap<>());
        this.carId = carId;
        this.type = type;
    }


    public void updatePrice(Double price){
        this.price = price;
    }

    public void addMetadata(String key, Object value){
        this.metadata.put(key, value);
    }

    public String getCarId() {
        return carId;
    }

    public PriceType getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void merge(CarLevelPrice price){
        Optional.ofNullable(price.getPrice()).ifPresent(this::updatePrice);
        Optional.ofNullable(price.getMetadata()).ifPresent(meta -> this.metadata.putAll(meta));
    }
}
