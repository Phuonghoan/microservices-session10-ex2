package org.example.pharmacyservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bill")
@RefreshScope
public class BillController {

    @Value("${pharmacy.vat-rate}")
    private double vatRate;

    @PostMapping
    public BillResponse calculateBill(
            @RequestBody BillRequest request
    ) {

        double medicineAmount = request.totalMedicineAmount();

        double vatAmount = medicineAmount * vatRate;

        double totalAmount = medicineAmount + vatAmount;

        return new BillResponse(
                medicineAmount,
                vatRate,
                vatAmount,
                totalAmount
        );
    }

    public record BillRequest(
            double totalMedicineAmount
    ) {
    }

    public record BillResponse(
            double medicineAmount,
            double vatRate,
            double vatAmount,
            double totalAmount
    ) {
    }
}
