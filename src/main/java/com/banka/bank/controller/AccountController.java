package com.banka.bank.controller;

import com.banka.bank.model.Account;
import com.banka.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/ekle/{customer_id}")
    public String addAccount(@RequestBody Account account, @PathVariable("customer_id") long customerId) {
        boolean isAddAccount = accountService.createAccount(account,customerId);
        if (isAddAccount)
            return "Account başarıyla oluşturuldu";
        else
            return "Account oluşturma işlemi başarısız";
    }

    @GetMapping("/balance/{account_id}")
    public double addAccount(@PathVariable("account_id") long accountId) {
        return accountService.getAmount(accountId);
    }

}
