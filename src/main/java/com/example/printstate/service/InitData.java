package com.example.printstate.service;

import com.example.printstate.entity.Article;
import com.example.printstate.entity.Client;
import com.example.printstate.entity.Facture;
import com.example.printstate.entity.LigneFacture;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;

/**
 * Classe permettant d'insérer des données dans l'application.
 */
@Service
@Transactional
public class InitData implements ApplicationListener<ApplicationReadyEvent> {

    private EntityManager entityManager;

    public InitData(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        insertTestData();
    }

    private void insertTestData() {

        // Création de client
        Client client1 = newClient("Collet", "Adrienne", LocalDate.of(1979, 11, 6));
        entityManager.persist(client1);

        Client client2 = newClient("Brunet", "Valérie", LocalDate.of(1997, 6, 11));
        entityManager.persist(client2);


        //entityManager.persist(newClient("ABSCHEN", "Jean", LocalDate.of(1996, 5, 14)));
        //entityManager.persist(newClient("ADAMO", "Stéphane", LocalDate.of(1987, 5, 14)));
        //entityManager.persist(newClient("AMELLAL", "Viviane", LocalDate.of(1996, 5, 14)));
        //entityManager.persist(newClient("ANGONIN", "Jean-Pierre", LocalDate.of(1991, 5, 14)));
        //entityManager.persist(newClient("AZOURA", "Marie-France", LocalDate.of(1996, 5, 14)));
        //entityManager.persist(newClient("AZRIA", "Maryse", LocalDate.of(1996, 1, 14)));
        //entityManager.persist(newClient("BACH", "Sylvie", LocalDate.of(1996, 5, 14)));
        //entityManager.persist(newClient("BARNAUD", "Janine", LocalDate.of(1993, 5, 14)));
        //entityManager.persist(newClient("BENSIMHON", "Pascal", LocalDate.of(1996, 5, 24)));
        //entityManager.persist(newClient("BERTRAND", "Roger", LocalDate.of(1996, 5, 14)));
        entityManager.persist(newClient("Hardy", "Thierry-Eugène", LocalDate.of(2000, 10, 25)));

        // Création d'articles
        Article a1 = createArticle("Chargeurs de téléphones Portables", 22.98, 9);
        Article a2 = createArticle("Playmobil Hydravion de Police", 14.39, 2);
        Article a3 = createArticle("Distributeur de croquettes pour chien", 12.99, 0);

        // création de factures
        Facture f1 = new Facture();
        f1.setClient(client1);
        entityManager.persist(f1);
        entityManager.persist(newLigneFacture(f1, a1, 2));
        entityManager.persist(newLigneFacture(f1, a2, 1));

        Facture f2 = new Facture();
        f2.setClient(client2);
        entityManager.persist(f2);
        entityManager.persist(newLigneFacture(f2, a1, 10));

        Facture f3 = new Facture();
        f3.setClient(client1);
        entityManager.persist(f3);
        entityManager.persist(newLigneFacture(f3, a3, 1));

    }

    private Article createArticle(String libelle, double prix, int stock) {
        Article a1 = new Article();
        a1.setLibelle(libelle);
        a1.setPrix(prix);
        a1.setStock(stock);
        entityManager.persist(a1);
        return a1;
    }


    private LigneFacture newLigneFacture(Facture f, Article a1, int quantite) {
        LigneFacture lf1 = new LigneFacture();
        lf1.setArticle(a1);
        lf1.setQuantite(quantite);
        lf1.setFacture(f);
        return lf1;
    }


    private Client newClient(String nom, String prenom, LocalDate dateNaissance) {
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setDateNaissance(dateNaissance);
        return client;
    }


}
