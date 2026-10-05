package org.example.pharmacyservice.controller;

import org.example.pharmacyservice.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/stock/{medicineId}")
    public Object checkStock(@PathVariable Long medicineId) {

        return warehouseService.checkStock(medicineId);
    }
}
