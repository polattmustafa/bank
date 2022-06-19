package com.banka.bank.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACTION")
@Data
public class Transaction extends BaseEntity{


    @Column
    private String currencyCode;

    @Column
    private double amount;

    @Column
    private String processType;

    @Column
    private boolean recordStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_account_id",nullable = true)
    private Account fromAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_account_id",nullable = false)
    private Account toAccount;

}
