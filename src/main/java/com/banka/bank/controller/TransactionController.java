package com.banka.bank.controller;

import com.banka.bank.entity.AccountSetBalanceDTO;
import com.banka.bank.entity.TransactionHistory;
import com.banka.bank.entity.TransferDTO;
import com.banka.bank.model.Transaction;
import com.banka.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/parayatir")
    public String paraYatir(@RequestBody AccountSetBalanceDTO setbalancedto){
        try {
            TransferDTO transferDto=new TransferDTO();
            transferDto.setToAccountId(setbalancedto.getAccountId());
            transferDto.setFromAccountId(0);
            transferDto.setAmount(setbalancedto.getAmount());
            transactionService.transferMoney(transferDto);
            return  "Para yatırıldı";
        }catch(Exception ex){
            ex.printStackTrace();
            return  "Para yatırma işlemi olmadı";
        }
    }
    @PostMapping("/paracekme")
    public String paraCekme(@RequestBody AccountSetBalanceDTO setbalancedto){
        try {
            TransferDTO transferDto=new TransferDTO();
            transferDto.setToAccountId(setbalancedto.getAccountId());
            transferDto.setFromAccountId(0);
            transferDto.setAmount(-1*setbalancedto.getAmount());
            transactionService.transferMoney(transferDto);
            return  "Para çekildi";
        }catch(Exception ex){
            ex.printStackTrace();
            return  "Para çekme işlemi olmadı";
        }
    }
    @PostMapping("/paragonderme")
    public String paraGonderme(@RequestBody TransferDTO transferDTO){
        try {
            transactionService.transferMoney(transferDTO);
            return  "Para gönderildi";
        }catch(Exception ex){
            ex.printStackTrace();
            return  "Para gönderme işlemi olmadı";
        }
    }

    @GetMapping("/islemtarihce/{account_id}")
    public List<TransactionHistory> getIslemTarihce(@PathVariable("account_id") long accountId){
        return transactionService.getIslemTarihce(accountId);
    }
}
