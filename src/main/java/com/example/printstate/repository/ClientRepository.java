package com.example.printstate.repository;

import com.example.printstate.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository permettant l'interaction avec la base de données pour les clients.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
