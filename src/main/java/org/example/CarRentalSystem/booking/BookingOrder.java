package org.example.CarRentalSystem.booking;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

public class BookingOrder {
    private String id;
    private String userId;
    private Instant from;
    private Instant to;
    private Double price;
    private Double finalPrice;
    private Map<String, Object> priceBreakup;
    private BookingStatus status;
}
