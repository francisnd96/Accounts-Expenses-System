package com.expense.manager.Controllers;

import com.expense.manager.Model.Account;
import com.expense.manager.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {

    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping(value = "/accounts")
    private ResponseEntity<Page<Account>> accounts(Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(this.accountRepository.findAll(pageable));
    }

    @GetMapping(value = "/accounts/{accountId}")
    private ResponseEntity<Account> accountById(@PathVariable Long accountId) {
        return this.accountRepository
                .findById(accountId)
                .map(ResponseEntity.accepted()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/accounts")
    private ResponseEntity<Account> addAccount(@RequestBody Account account) {
        return ResponseEntity
                .ok()
                .body(this.accountRepository.save(account));
    }

//    @PutMapping(value = "/accounts/{accountId}")
//    private ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable Long accountId) {
//        return this.accountRepository
//                .findById(accountId)
//                .map(accountFound -> {
//                    accountFound.mergeAccount(account);
//                    return ResponseEntity
//                            .accepted()
//                            .body(this.accountRepository.save(account));
//                }).orElse(ResponseEntity.notFound().build());
//    }

    @DeleteMapping(value = "/accounts/{accountId}")
    private ResponseEntity deleteAccount(@PathVariable Long accountId) {
        return this.accountRepository
                .findById(accountId)
                .map(accountFound -> {
                    this.accountRepository.delete(accountFound);
                    return ResponseEntity
                            .accepted()
                            .build();
                }).orElse(ResponseEntity.notFound().build());
    }
}