package org.example.pharmacyservice.service;

import org.example.pharmacyservice.event.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {

    private static final String TOPIC = "medicine-stock-events";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderEvent event) {

        String key = String.valueOf(event.getMedicineId());

        kafkaTemplate.send(
                TOPIC,
                key,
                event
        );
    }
}