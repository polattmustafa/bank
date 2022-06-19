package com.banka.bank.service;

import com.banka.bank.model.Bank;
import com.banka.bank.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.Date;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    public boolean createBank(Bank bank) {
        try {
            bank.setCreatedDate(new Date());
            bankRepository.save(bank);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public Bank getBankById(long id){
      return bankRepository.getById(id);
    }

}
