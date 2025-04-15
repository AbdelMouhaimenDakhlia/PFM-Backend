package com.pfm.pfmbackend.model;

import jakarta.persistence.*;


@Entity
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String dateTrans ;

    @Column(nullable = false)
    private Double montant;

    private String description;

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutTransaction statut = StatutTransaction.Réalisée;

    @ManyToOne
    @JoinColumn(name = "compt_id", nullable = false)
    private ComptBancaire compteBancaire;

    public enum TypeTransaction {
        C, D
    }

    public enum StatutTransaction {
        Réalisée, PRÉVUE
    }


    // Constructeur sans arguments
    public Transaction() {}

    // Constructeur avec arguments
    public Transaction(Long id, String dateTrans, Double montant, String description, TypeTransaction type, StatutTransaction statut, ComptBancaire compteBancaire) {
        this.id = id;
        this.dateTrans = dateTrans;
        this.montant = montant;
        this.description = description;
        this.type = type;
        this.statut = statut;
        this.compteBancaire = compteBancaire;
    }



    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateTrans() {
        return dateTrans;
    }

    public void setDateTrans(String dateTrans) {
        this.dateTrans = dateTrans;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeTransaction getType() {
        return type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public StatutTransaction getStatut() {
        return statut;
    }

    public void setStatut(StatutTransaction statut) {
        this.statut = statut;
    }

    public ComptBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(ComptBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }



}
