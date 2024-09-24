package com.kirana.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kirana.app.entity.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String>{

	 List<Transaction> findByDateBetween(Date startDate, Date endDate);
}
