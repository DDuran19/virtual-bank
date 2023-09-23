package main.com.virtualbank.service;

import main.com.virtualbank.enums.TransactionTypes;

import java.time.LocalDateTime;


public class TransactionLog {
    private final TransactionTypes transactionType;
    private final LocalDateTime timestamp;
    private final String details;

    public TransactionLog(TransactionTypes transactionType, LocalDateTime timestamp, String details) {
        this.transactionType = transactionType;
        this.timestamp = timestamp;
        this.details = details;

    }
}
