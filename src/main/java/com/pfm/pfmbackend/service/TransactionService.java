package com.pfm.pfmbackend.service;

import com.pfm.pfmbackend.model.ComptBancaire;
import com.pfm.pfmbackend.model.Transaction;
import com.pfm.pfmbackend.repository.ComptBancaireRepository;
import com.pfm.pfmbackend.repository.TransactionRepository;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ComptBancaireRepository comptBancaireRepository;

    public TransactionService(TransactionRepository transactionRepository, ComptBancaireRepository comptBancaireRepository) {
        this.comptBancaireRepository = comptBancaireRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction ajouterTransaction(Long compteId, Transaction transaction) {
        ComptBancaire compte = comptBancaireRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        transaction.setCompteBancaire(compte);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> listerTransactions(Long compteId) {
        ComptBancaire compte = comptBancaireRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        return transactionRepository.findByCompteBancaire(compte);
    }
    public List<Transaction> listerToutesLesTransactions() {
        // Récupérer toutes les transactions
        return transactionRepository.findAll();
    }
}
