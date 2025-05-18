package com.pfm.pfmbackend.repository;

import com.pfm.pfmbackend.model.ComptBancaire;
import com.pfm.pfmbackend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCompteBancaire(ComptBancaire compte);

    boolean existsByMontantAndDescriptionAndDateAndTypeAndCompteBancaire(
            Double montant, String description, LocalDate date, String type, ComptBancaire compte);

    @Query("SELECT t FROM Transaction t WHERE t.montant = :montant AND t.description = :description AND t.date = :date AND t.type = :type AND t.compteBancaire = :compteBancaire")
    List<Transaction> findMatchingTransaction(
            @Param("montant") Double montant,
            @Param("description") String description,
            @Param("date") LocalDate date,
            @Param("type") String type,
            @Param("compteBancaire") ComptBancaire compteBancaire
    );



}

