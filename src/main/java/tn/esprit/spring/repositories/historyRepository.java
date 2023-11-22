package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.HistoriqueEntity;

@Repository
//D�claration de l'interface historyRepository, qui �tend JpaRepository pour l'entit� HistoriqueEntity.
public interface historyRepository extends JpaRepository<HistoriqueEntity, Long> {

	// M�thode de recherche des entit�s HistoriqueEntity par l'attribut "user".
	List<HistoriqueEntity> findByUser(String user);
}
