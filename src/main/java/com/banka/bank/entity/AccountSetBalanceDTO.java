package com.banka.bank.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class AccountSetBalanceDTO implements Serializable {
    private long accountId;
    private double amount;
}
