package com.kirana.app.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transactions")
public class Transaction {
	
	@Id
    private String id;
	@NotNull(message = "Amount cannot be null")  // Ensure amount is not null
    @Positive(message = "Amount must be positive")
    private double amount;
	@NotNull(message = "Currency cannot be null")
    private String currency;
	@NotNull(message = "Transaction type cannot be null") 
    private String type;  // "credit" or "debit"
    private Date date;

}
