package com.pfm.pfmbackend.controller;

import com.pfm.pfmbackend.model.Transaction;
import com.pfm.pfmbackend.service.TransactionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{compteId}")
    public ResponseEntity<Transaction> ajouterTransaction(
            @PathVariable Long compteId, @RequestBody Transaction transaction) {
        Transaction newTransaction = transactionService.ajouterTransaction(compteId, transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
    }

    @GetMapping("/{compteId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long compteId) {
        return ResponseEntity.ok(transactionService.listerTransactions(compteId));
    }
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        // Appeler le service pour récupérer toutes les transactions
        List<Transaction> transactions = transactionService.listerToutesLesTransactions();
        return ResponseEntity.ok(transactions);
    }
}
