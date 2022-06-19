package com.banka.bank.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer extends BaseEntity{

    @Column(unique = true, length = 50)
    private String email;

    @Column
    private String customerName;

    @Column
    private String customerSurname;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "customer")
    private Collection<Account> accounts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_id",nullable = false)
    private Bank bank;
}
