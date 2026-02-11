package org.example.CarRentalSystem.booking;

import org.example.CarRentalSystem.Inventory.InventoryService;
import org.example.CarRentalSystem.catalog.Car;
import org.example.CarRentalSystem.filter.FilterProxy;
import org.example.CarRentalSystem.filter.FilterQuery;
import org.example.CarRentalSystem.payments.PaymentService;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookingServiceImpl implements BookingService {
    private Map<String, BookingOrder> bookingOrderMap;
    private Map<String, BookingItem> bookingItemMap;
    private Map<String, List<String>> orderToItemsIndex;

    private InventoryService inventoryService;
    private PaymentService paymentService;
    private FilterProxy filterProxy;

    public BookingServiceImpl(){
        this.bookingOrderMap = new ConcurrentHashMap<>();
        this.bookingItemMap = new ConcurrentHashMap<>();
        this.orderToItemsIndex = new ConcurrentHashMap<>();
        this.filterProxy = FilterProxy.getInstance();
        this.inventoryService = InventoryService.getInstance();
        this.paymentService = PaymentService.getInstance();
    }


    @Override
    public List<Car> checkAvailability(FilterQuery query) {
        return List.of();
    }

    @Override
    public String reserve(String carId, String userId, Instant from, Instant to) {
        return "";
    }

    @Override
    public ModificationInfo modificationIntent(String bookingId, Instant from, Instant to) {
        return null;
    }

    @Override
    public String modify(ModificationInfo info) {
        return "";
    }

    @Override
    public CancellationInfo cancellationIntent(String bookingId) {
        return null;
    }

    @Override
    public String cancel(CancellationInfo info) {
        return "";
    }
}
