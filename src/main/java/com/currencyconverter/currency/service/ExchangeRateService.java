package com.currencyconverter.currency.service;

import com.currencyconverter.currency.models.Currency;
import com.currencyconverter.currency.models.ExchangeRateResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeRateService {

    private static final String API_URL = "https://open.er-api.com/v6/latest/";
    private static final String HISTORICAL_API_URL = "https://api.exchangerate.host/";

    private final RestTemplate restTemplate = new RestTemplate();

    private static final Map<String, String> CURRENCY_NAMES;
    static {
        CURRENCY_NAMES = new HashMap<>();
        CURRENCY_NAMES.put("USD", "United States Dollar");
        CURRENCY_NAMES.put("EUR", "Euro");
        CURRENCY_NAMES.put("GBP", "British Pound");
        CURRENCY_NAMES.put("CAD", "Canadian Dollar");
        CURRENCY_NAMES.put("AUD", "Australian Dollar");
        CURRENCY_NAMES.put("JPY", "Japanese Yen");
        CURRENCY_NAMES.put("CNY", "Chinese Yuan");
        CURRENCY_NAMES.put("INR", "Indian Rupee");
        CURRENCY_NAMES.put("BRL", "Brazilian Real");
        CURRENCY_NAMES.put("ZAR", "South African Rand");
        CURRENCY_NAMES.put("ETB", "Ethiopian Birr");
    }

    public Map<String, Double> getRates(String baseCurrency) {
        String url = API_URL + baseCurrency;
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

        if (response != null && "success".equals(response.getResult())) {
            return response.getRates();
        } else {
            throw new RuntimeException("Failed to fetch exchange rates");
        }
    }

    public Double getSpecificRate(String from, String to) {
        Map<String, Double> rates = getRates(from);
        return rates.get(to);
    }

    public List<Currency> getCurrencies() {
        Map<String, Double> rates = getRates("USD");
        List<Currency> currencies = new ArrayList<>();
        for (String code : rates.keySet()) {
            String name = CURRENCY_NAMES.getOrDefault(code, code);
            currencies.add(new Currency(code, name));
        }
        return currencies;
    }

    public List<Map<String, Object>> getHistoricalRates(String from, String to) {
        List<Map<String, Object>> historicalData = new ArrayList<>();
        LocalDate endDate = LocalDate.now();
        for (int i = 6; i >= 0; i--) {
            LocalDate date = endDate.minusDays(i);
            String url = HISTORICAL_API_URL + date.toString() + "?base=" + from;
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            if (response != null && response.get("success").equals(true)) {
                Map<String, Double> rates = (Map<String, Double>) response.get("rates");
                historicalData.add(Map.of("date", date.toString(), "rate", rates.get(to)));
            }
        }
        return historicalData;
    }
}