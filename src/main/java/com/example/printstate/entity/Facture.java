package com.example.printstate.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Entity représentant une facture.
 */
@Entity
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
    private Set<LigneFacture> ligneFactures;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<LigneFacture> getLigneFactures() {
        return ligneFactures;
    }

    public void setLigneFactures(Set<LigneFacture> ligneFactures) {
        this.ligneFactures = ligneFactures;
    }

    public Double getTotal() {
        Double total = 0.0;
        for (LigneFacture ligneFacture : ligneFactures) {
            total = total + ligneFacture.getSousTotal();
        }
        return total;
    }
}
