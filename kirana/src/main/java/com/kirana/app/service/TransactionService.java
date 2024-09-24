package com.kirana.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kirana.app.entity.Transaction;
import com.kirana.app.exception.CurrencyConversionException;
import com.kirana.app.exception.TransactionException;
import com.kirana.app.repository.TransactionRepository;

@Service
public class TransactionService {
	
	 @Autowired
	 private TransactionRepository transactionRepository;
	 
	 @Autowired
	 private CurrencyConversionService currencyConversionService;
	 
	 public Transaction saveTransactionWithConversion(Transaction transaction, String targetCurrency) {
	        // Fetch the exchange rates from CurrencyConversionService
		 try {
	        Map<String, Object> exchangeRates = currencyConversionService.getExchangeRates();

	        // Perform currency conversion if necessary
	        if (!transaction.getCurrency().equalsIgnoreCase(targetCurrency)) {
	            double conversionRate = getConversionRate(transaction.getCurrency(), targetCurrency, exchangeRates);
	            double convertedAmount = transaction.getAmount() * conversionRate;
	            transaction.setAmount(convertedAmount);  // Update the transaction amount with converted value
	            transaction.setCurrency(targetCurrency); // Set the new currency
	        }
	        transaction.setDate(new Date());
	        // Save the transaction after conversion
	        return transactionRepository.save(transaction);
	    }catch (Exception e) {
            throw new TransactionException("Failed to process the transaction", e);
        }
    }
	 
	 private double getConversionRate(String fromCurrency, String toCurrency, Map<String, Object> exchangeRates) {
		    // Get the exchange rates from the response object
		 try {
		    Map<String, Object> rates = (Map<String, Object>) exchangeRates.get("rates");

		    // Get the exchange rates for both 'fromCurrency' and 'toCurrency'
		    double fromRate = getRateAsDouble(rates.get(fromCurrency.toUpperCase()));
		    double toRate = getRateAsDouble(rates.get(toCurrency.toUpperCase()));

		    return toRate / fromRate;  // Convert from 'fromCurrency' to 'toCurrency'
		}catch (Exception e) {
            throw new CurrencyConversionException("Failed to convert currency from " + fromCurrency + " to " + toCurrency, e);
        }
    }

		private double getRateAsDouble(Object rate) {
		    if (rate instanceof Integer) {
		        return ((Integer) rate).doubleValue();  // Convert Integer to Double
		    } else if (rate instanceof Double) {
		        return (Double) rate;  // Directly return Double
		    } else {
	            throw new CurrencyConversionException("Unexpected rate type: " + rate.getClass().getSimpleName());
		    }
		}

	 public Transaction saveTransaction(Transaction transaction) {
	        transaction.setDate(new Date()); // Automatically set the current date
	        return transactionRepository.save(transaction);
	    }

	public List<Transaction> getTransactionsBetween(Date startDate, Date endDate) {
	       return transactionRepository.findByDateBetween(startDate, endDate);
	    }
	

}
