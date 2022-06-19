package com.banka.bank.service;

import com.banka.bank.model.Bank;
import com.banka.bank.model.Customer;
import com.banka.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BankService bankService;

    public boolean createCustomer(Customer customer,long bankId) {
        try {
            Bank bank =bankService.getBankById(bankId);
            customer.setCreatedDate(new Date());
            customer.setBank(bank);
            customerRepository.save(customer);
            return true;
        }catch (Exception ex) {
            return false;
        }
    }

    public Customer getCostumerById(long id) {
        return customerRepository.getById(id);
    }

}
