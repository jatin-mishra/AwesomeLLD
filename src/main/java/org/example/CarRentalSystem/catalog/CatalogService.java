package org.example.CarRentalSystem.catalog;

import org.example.CarRentalSystem.filter.Filter;
import org.example.CarRentalSystem.filter.FilterQuery;

import java.util.List;

public class CatalogService implements Filter {
    private static CatalogService instance = new CatalogService();

    private CatalogService(){
    }

    public static CatalogService getInstance(){
        return instance;
    }

    @Override
    public List<String> filter(List<String> ids, FilterQuery query) {
        return List.of();
    }


}
