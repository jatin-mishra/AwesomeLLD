package org.example.CarRentalSystem.catalog;

public class Car {
    private String id;
    private CarType type;
    private String model;
    private String plateNumber;
    private Limit limit;
    public Limit getLimit() {
        return limit;
    }
    public String getId() {
        return id;
    }
    public CarType getType() {
        return type;
    }
    public String getModel() {
        return model;
    }
    public String getPlateNumber() {
        return plateNumber;
    }
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
    public void setLimit(Limit limit) {
        this.limit = limit;
    }
}
