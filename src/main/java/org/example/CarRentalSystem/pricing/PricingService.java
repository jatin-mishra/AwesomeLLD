package org.example.CarRentalSystem.pricing;



public interface PricingService<T extends CarPrice> {
    void addPrice(T carPrice);
}
