package org.example.CarRentalSystem.payments;

import java.time.Instant;

public class Ledger {
    private String id;
    private String bookingId;
    private PaymentType type;
    private Double price;
    private PaymentStatus status;
    private String userId;
    private Instant createdAt;
    private Instant updatedAt;
}
