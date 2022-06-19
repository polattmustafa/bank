package com.banka.bank.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "ACCOUNT")
@Data
public class Account extends BaseEntity{

    @Column(unique = true)
    private String accountName;

    @Column
    private String accountPassword;

    @Column
    private String currencyCode;

    @Column
    private double balance;

    @Column
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;
}
