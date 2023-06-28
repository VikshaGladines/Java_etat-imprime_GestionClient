package com.example.printstate.repository;

import com.example.printstate.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository permettant l'interaction avec la base de données pour les factures.
 */
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
}

