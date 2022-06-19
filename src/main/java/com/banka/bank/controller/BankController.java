package com.banka.bank.controller;

import com.banka.bank.model.Bank;
import com.banka.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    BankService bankService;

    @PostMapping("/ekle")
    public String addBank(@RequestBody Bank bank){
        boolean sonuc = bankService.createBank(bank);
        if(sonuc){
            return "Banka oluşturuldu";
        }else{
            return "Banka oluşturulamadı";
        }
    }

}
