// src/main/java/com/currencyconverter/currency/models/ExchangeRateResponse.java
package com.currencyconverter.currency.models;

import java.util.Map;

public class ExchangeRateResponse {
    private String result;
    private Map<String, Double> rates;

    // Getters and setters
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
