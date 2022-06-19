package com.banka.bank.service;

import com.banka.bank.model.Account;
import com.banka.bank.model.Customer;
import com.banka.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerService customerService;

    public boolean createAccount(Account account, long customerId) {
        try {
            Customer customer = customerService.getCostumerById(customerId);
            account.setCreatedDate(new Date());
            account.setCustomer(customer);
            accountRepository.save(account);
            return true;
        }catch (Exception ex) {
            return false;
        }

    }

    public Account getAccountById(long id) {
        return accountRepository.getById(id);
    }

    public double getAmount(long accountId){
        Account account = accountRepository.getById(accountId);
        return account.getBalance();
    }

}
