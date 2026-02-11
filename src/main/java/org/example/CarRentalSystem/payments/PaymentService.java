package org.example.CarRentalSystem.payments;


public class PaymentService {
    private static PaymentService instance = new PaymentService();

    private PaymentService(){}

    public static PaymentService getInstance(){
        return instance;
    }
}
