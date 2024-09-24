package com.kirana.app.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyConversionService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Cacheable("currencyRates")
    public Map<String, Object> getExchangeRates() {
        String url = "https://api.fxratesapi.com/latest";
        return restTemplate.getForObject(url, Map.class);
    }
}

