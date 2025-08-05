package com.currencyconverter.currency.controller;

import com.currencyconverter.currency.models.Currency;
import com.currencyconverter.currency.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ExchangeController {

    @Autowired
    private ExchangeRateService service;

    @GetMapping("/currencies")
    public ResponseEntity<List<Currency>> getCurrencies() {
        try {
            List<Currency> currencies = service.getCurrencies();
            return ResponseEntity.ok(currencies);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/rates/{base}")
    public ResponseEntity<Map<String, Double>> getRates(@PathVariable String base) {
        try {
            Map<String, Double> rates = service.getRates(base);
            return ResponseEntity.ok(rates);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/rate/{from}/{to}")
    public ResponseEntity<Double> getSpecificRate(@PathVariable String from, @PathVariable String to) {
        try {
            Double rate = service.getSpecificRate(from, to);
            return ResponseEntity.ok(rate);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/historical/{from}/{to}")
    public ResponseEntity<List<Map<String, Object>>> getHistoricalRates(@PathVariable String from, @PathVariable String to) {
        try {
            List<Map<String, Object>> historicalRates = service.getHistoricalRates(from, to);
            return ResponseEntity.ok(historicalRates);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}