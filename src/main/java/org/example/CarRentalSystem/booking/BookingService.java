package org.example.CarRentalSystem.booking;

import org.example.CarRentalSystem.catalog.Car;
import org.example.CarRentalSystem.filter.FilterQuery;

import java.time.Instant;
import java.util.List;

public interface BookingService {
    List<Car> checkAvailability(FilterQuery query);
    String reserve(String carId, String userId, Instant from, Instant to);
    ModificationInfo modificationIntent(String bookingId, Instant from, Instant to);
    String modify(ModificationInfo info);
    CancellationInfo cancellationIntent(String bookingId);
    String cancel(CancellationInfo info);
}
