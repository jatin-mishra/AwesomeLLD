package org.example.CarRentalSystem.filter;

import org.example.CarRentalSystem.catalog.CatalogService;
import org.example.CarRentalSystem.pricing.PricingServiceImplementation;

import java.util.List;
import java.util.Map;

public class FilterProxy {
    private static FilterProxy instance = new FilterProxy();

    public static FilterProxy getInstance(){
        return instance;
    }
    Map<FilterType, Filter> filterTypeFilterMap;

    private FilterProxy(){
        filterTypeFilterMap = Map.of(
                FilterType.Pricing, PricingServiceImplementation.getInstance(),
                FilterType.CarType, CatalogService.getInstance()
        );
    }

    public List<String> filter(List<FilterType> typeList, List<String> ids, FilterQuery query){
        List<String> input = ids;
        for(FilterType filterType : typeList){
            input = filterTypeFilterMap.get(filterType).filter(ids, query);
        }
        return input;
    }
}
