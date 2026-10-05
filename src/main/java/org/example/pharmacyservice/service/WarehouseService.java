package org.example.pharmacyservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WarehouseService {

    private final RestClient restClient;

    public WarehouseService(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8085")
                .build();
    }

    @CircuitBreaker(
            name = "warehouseCB",
            fallbackMethod = "checkWarehouseFallback"
    )
    public Object checkStock(Long medicineId) {

        return restClient.get()
                .uri("/api/v1/warehouse/stock/" + medicineId)
                .retrieve()
                .body(Object.class);
    }

    public record WarehouseError(
            int status,
            String error,
            String message
    ) {
    }

    public record WarehouseFallbackResponse(
            int status,
            String error,
            String message
    ) {
    }
}
