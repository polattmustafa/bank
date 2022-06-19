package com.banka.bank.service;

import com.banka.bank.entity.TransactionHistory;
import com.banka.bank.entity.TransferDTO;
import com.banka.bank.model.Account;
import com.banka.bank.model.Transaction;
import com.banka.bank.repository.AccountRepository;
import com.banka.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;


    @Transactional // işlem bütünlüğünmü sağlar
    public void transferMoney(TransferDTO transferDTO){
       Account fromAccount=null;

       if(transferDTO.getFromAccountId()>0)
            fromAccount=accountService.getAccountById(transferDTO.getFromAccountId());

       String processType="";
       if(fromAccount==null){
           // kendimden para çektim veya yatırdım.
           if(transferDTO.getAmount()>0)
               processType="PARAYATIRMA";
           else
               processType="PARACEKME";
       }else{
           processType="PARAGONDERME";
       }
       Account toAccount = accountService.getAccountById(transferDTO.getToAccountId());

       if(processType=="PARAYATIRMA"){
           toAccount.setBalance(toAccount.getBalance()+transferDTO.getAmount());
       }else if(processType=="PARACEKME"){
           toAccount.setBalance(toAccount.getBalance()+transferDTO.getAmount());
       }else if (processType=="PARAGONDERME"){
           toAccount.setBalance(toAccount.getBalance()+transferDTO.getAmount());
           fromAccount.setBalance(fromAccount.getBalance()-transferDTO.getAmount());
       }
       accountRepository.save(toAccount);
       if(fromAccount!=null)
            accountRepository.save(fromAccount);

       Transaction transaction=new Transaction();
       transaction.setAmount(transferDTO.getAmount());
       if(fromAccount!=null){
           transaction.setFromAccount(fromAccount);
       }
       transaction.setToAccount(toAccount);
       transaction.setCurrencyCode("TRY");
       transaction.setCreatedDate(new Date());
       transaction.setProcessType(processType);
       transaction.setRecordStatus(true);

       transactionRepository.save(transaction);

   }

   public List<TransactionHistory> getIslemTarihce(long accountId){
       List<Transaction> transactions= transactionRepository.getIslemTarihce(accountId);
       List<TransactionHistory> histories=new ArrayList<>();
       for (Transaction transaction:transactions) {
           TransactionHistory history=new TransactionHistory();
           history.setAmount(transaction.getAmount());
           history.setCreatedDate(transaction.getCreatedDate());
           history.setProcessType(transaction.getProcessType());
           if(transaction.getFromAccount()!=null) {
               history.setFromAccountId(transaction.getFromAccount().getId());
               history.setFromCustomerName(transaction.getFromAccount().getCustomer().getCustomerName() + " " + transaction.getFromAccount().getCustomer()
                       .getCustomerSurname());
           }
           history.setToAccountId(transaction.getToAccount().getId());
           history.setToCustomerName(transaction.getToAccount().getCustomer().getCustomerName()+transaction.getToAccount().getCustomer().getCustomerSurname());
           histories.add(history);
       }
       return histories;
   }
}
