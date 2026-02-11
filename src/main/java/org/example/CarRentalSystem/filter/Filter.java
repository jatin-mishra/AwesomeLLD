package org.example.CarRentalSystem.filter;


import java.util.List;

public interface Filter {
    List<String> filter(List<String> ids, FilterQuery query);
}
