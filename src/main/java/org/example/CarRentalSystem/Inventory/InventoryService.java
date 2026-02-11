package org.example.CarRentalSystem.Inventory;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryService {
    private static InventoryService instance = new InventoryService();
    private int maxLimitInDays = 7;

    private Map<LocalDate, Set<Inventory>> inventory;

    private InventoryService(){
        inventory = new ConcurrentHashMap<>();
    }

    public static InventoryService getInstance(){
        return instance;
    }

    public List<String> getAvailability(Instant from, Instant to){
        // might need read lock over inventories
        if(to.isBefore(from)) return new ArrayList<>();
        if(from.plus(maxLimitInDays, ChronoUnit.DAYS).isBefore(to)){
            // log
            return new ArrayList<>();
        }

        LocalDate start = from.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = from.atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate iterator = start;
        Set<String> cars = new HashSet<>();
        while(iterator.isBefore(end) || iterator.isEqual(end)){
            Set<String> carsForDate = new HashSet<>();
            for(Inventory inv : inventory.getOrDefault(iterator, new HashSet<>())){
                if(inv.isAvailable()){
                    carsForDate.add(inv.getCarId());
                }
            }

            if(iterator.isEqual(start)) cars.addAll(carsForDate);
            else cars.retainAll(carsForDate);

            if(cars.isEmpty()) return new ArrayList<>();
            iterator = iterator.plusDays(1);
        }

        return new ArrayList<>(cars);
    }
}
