package org.example.pharmacyservice.controller;

import org.example.pharmacyservice.event.OrderEvent;
import org.example.pharmacyservice.service.OrderEventProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/pharmacy")
public class PharmacyController {

    private final OrderEventProducer orderEventProducer;

    public PharmacyController(OrderEventProducer orderEventProducer) {
        this.orderEventProducer = orderEventProducer;
    }

    @PostMapping("/sell")
    public ResponseEntity<?> sellMedicine(
            @RequestBody OrderEvent event
    ) {

        event.setTimestamp(LocalDateTime.now());

        orderEventProducer.sendOrderEvent(event);

        return ResponseEntity.ok(
                "Thanh toán thành công và đã gửi OrderEvent lên Kafka."
        );
    }
}