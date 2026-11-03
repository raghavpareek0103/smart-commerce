package com.raghav.ecommerce.service;

import com.raghav.ecommerce.model.Order;
import com.raghav.ecommerce.model.Seller;
import com.raghav.ecommerce.model.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Order order);
    List<Transaction> getTransactionBySeller(Seller seller);
    List<Transaction>getAllTransactions();
}
