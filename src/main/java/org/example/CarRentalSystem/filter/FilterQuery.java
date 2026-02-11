package org.example.CarRentalSystem.filter;

import org.example.CarRentalSystem.catalog.CarType;

import java.time.Instant;

public class FilterQuery {
    private CarType carType;
    private Double minPricePerDay;
    private Double maxPricePerDay;
    private Instant from;
    private Instant to;
}
