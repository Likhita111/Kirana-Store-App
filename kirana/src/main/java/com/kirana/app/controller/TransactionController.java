package com.kirana.app.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kirana.app.config.ApiRateLimiter;
import com.kirana.app.entity.Transaction;
import com.kirana.app.service.CurrencyConversionService;
import com.kirana.app.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	
	@Autowired
    private TransactionService transactionService;
	
	@Autowired
	private ApiRateLimiter apiRateLimiter;
	
	
	@Autowired
    private CurrencyConversionService currencyConversionService;
	
	@GetMapping("/report")
    public List<Transaction> generateReport(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return transactionService.getTransactionsBetween(startDate, endDate);
    }
	
	@PostMapping
	public ResponseEntity<?> recordTransaction(@Valid @RequestBody Transaction transaction ,@RequestParam String targetCurrency) {
	    if (!apiRateLimiter.tryAcquire()) {
	        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Rate limit exceeded");
	    }
	    return ResponseEntity.ok(transactionService.saveTransactionWithConversion(transaction, targetCurrency));
	}
	
    // Expose the exchange rates API
    @GetMapping("/currency/rates")
    public Map<String, Object> getExchangeRates() {
        return currencyConversionService.getExchangeRates();
    }

}
