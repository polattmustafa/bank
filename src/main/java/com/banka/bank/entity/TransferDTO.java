package com.banka.bank.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class TransferDTO implements Serializable {
    private long fromAccountId;
    private long toAccountId;
    private double amount;
}
