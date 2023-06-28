package com.example.printstate.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Entity représentant un client.
 */
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String nom;

    @Column
    private LocalDate dateNaissance;

    @Column
    private int age;

    @OneToMany(mappedBy = "client")
    private List<Facture> factures;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public int getAge() { return 2022 - dateNaissance.getYear();}

    public void setAge(int age) { this.age = age;}

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }
}
