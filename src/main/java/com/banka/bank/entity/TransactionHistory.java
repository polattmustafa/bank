package com.banka.bank.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionHistory {
    private long fromAccountId;
    private String fromCustomerName;
    private long toAccountId;
    private String toCustomerName;
    private double amount;
    private String processType;
    private Date createdDate;

}
