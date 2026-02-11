package org.example.CarRentalSystem.Inventory;

import org.example.CarRentalSystem.catalog.CarType;

import java.util.Objects;

public class Inventory {
    private String carId;
    private CarType carType;
    private InventoryState state;
    private String geoHash;

    public Inventory(String carId, CarType carType, String geoHash) {
        this.carId = carId;
        this.carType = carType;
        this.geoHash = geoHash;
        this.state = InventoryState.Available;
    }

    synchronized public boolean reserve(){
        if(InventoryState.Available.equals(this.state)){
            this.state = InventoryState.Reserved;
            return true;
        }
        return false;
    }

    synchronized public boolean release(){
        if(InventoryState.Reserved.equals(this.state)){
            this.state = InventoryState.Available;
            return true;
        }
        return false;
    }

    public boolean isAvailable(){
        return InventoryState.Available.equals(this.state);
    }

    public void updateCarLocation(String geoHash){
        this.geoHash = geoHash;
    }

    public String getCarId() {
        return carId;
    }

    public InventoryState getState() {
        return state;
    }

    public CarType getCarType() {
        return carType;
    }

    public String getGeoHash() {
        return geoHash;
    }

    @Override
    public boolean equals(Object other){
        if(this == other) return true;
        if(!(other instanceof Inventory)) return false;
        Inventory inventory = (Inventory) other;
        return this.carId.equals(inventory.carId) && this.state.equals(inventory.state);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.carId, this.state);
    }
}
