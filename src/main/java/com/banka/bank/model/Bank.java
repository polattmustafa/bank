package com.banka.bank.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "BANK")
@Data
public class Bank extends BaseEntity {

    @Column
    private String bankName;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "bank")
    private Collection<Customer> customers;



}
