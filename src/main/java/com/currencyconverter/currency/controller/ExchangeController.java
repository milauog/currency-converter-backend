package com.currencyconverter.currency.controller;

import com.currencyconverter.currency.models.Currency;
import com.currencyconverter.currency.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://currency-converter-41c0c.web.app",
        "https://currency-converter-41c0c.firebaseapp.com"
})
public class ExchangeController {

    @Autowired
    private ExchangeRateService service;

    @GetMapping("/currencies")
    public ResponseEntity<?> getCurrencies() {
        try {
            return ResponseEntity.ok(service.getCurrencies());
        } catch (Exception e) {
            return errorResponse(e, "Failed to fetch currencies");
        }
    }

    @GetMapping("/rates/{base}")
    public ResponseEntity<?> getRates(@PathVariable String base) {
        try {
            return ResponseEntity.ok(service.getRates(base));
        } catch (Exception e) {
            return errorResponse(e, "Failed to fetch rates");
        }
    }

    @GetMapping("/rate/{from}/{to}")
    public ResponseEntity<?> getSpecificRate(@PathVariable String from, @PathVariable String to) {
        try {
            return ResponseEntity.ok(service.getSpecificRate(from, to));
        } catch (Exception e) {
            return errorResponse(e, "Failed to fetch exchange rate");
        }
    }

    @GetMapping("/historical/{from}/{to}")
    public ResponseEntity<?> getHistoricalRates(@PathVariable String from, @PathVariable String to) {
        try {
            return ResponseEntity.ok(service.getHistoricalRates(from, to));
        } catch (Exception e) {
            return errorResponse(e, "Failed to fetch historical rates");
        }
    }

    private ResponseEntity<Map<String, Object>> errorResponse(Exception e, String error) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "error", error,
                        "message", e.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }
}