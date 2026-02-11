package org.example.CarRentalSystem.pricing;


import org.example.CarRentalSystem.filter.Filter;
import org.example.CarRentalSystem.filter.FilterQuery;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PricingServiceImplementation implements Filter, PricingService<CarLevelPrice>  {
    private Map<PriceType, Map<String, CarLevelPrice>> priceMap;

    private static PricingServiceImplementation instance = new PricingServiceImplementation();

    public static PricingServiceImplementation getInstance(){
        return instance;
    }

    private PricingServiceImplementation() {
        this.priceMap = new ConcurrentHashMap<>();
        for(PriceType type : PriceType.values()){
            priceMap.putIfAbsent(type, new ConcurrentHashMap<>());
        }
    }

    public void addPrice(CarLevelPrice carLevelPrice){
        priceMap.get(carLevelPrice.getType())
                .putIfAbsent(carLevelPrice.getCarId(), carLevelPrice);
    }

    @Override
    public List<String> filter(List<String> ids, FilterQuery query) {
        return List.of();
    }
}
