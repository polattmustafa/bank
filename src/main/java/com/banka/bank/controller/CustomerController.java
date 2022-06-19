package com.banka.bank.controller;

import com.banka.bank.model.Customer;
import com.banka.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/ekle/{bank_id}")
    public String addCustomer(@RequestBody Customer customer, @PathVariable("bank_id") long bankId) {
       boolean isAddCustomer = customerService.createCustomer(customer,bankId);
       if (isAddCustomer) {
           return "Müşteri başarıyla oluşturuldu";
       }else {
           return "Müşteri oluşturma işlemi başarısız";
       }
    }

}
