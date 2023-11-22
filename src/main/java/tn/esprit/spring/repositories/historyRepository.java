package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.HistoriqueEntity;

@Repository
//Déclaration de l'interface historyRepository, qui étend JpaRepository pour l'entité HistoriqueEntity.
public interface historyRepository extends JpaRepository<HistoriqueEntity, Long> {

	// Méthode de recherche des entités HistoriqueEntity par l'attribut "user".
	List<HistoriqueEntity> findByUser(String user);
}
