package org.example.pharmacyservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PharmacyController {

    @Value("${app.branch-name}")
    private String branchName;

    @Value("${app.hotline}")
    private String hotline;

    @GetMapping("/api/v1/pharmacy/info")
    public String getPharmacyInfo() {
        return "Chi nhánh: " + branchName
                + " - Hotline: " + hotline;
    }
}
