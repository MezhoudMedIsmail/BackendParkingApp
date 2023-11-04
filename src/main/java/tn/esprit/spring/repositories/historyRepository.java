package tn.esprit.spring.repositories;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.HistoriqueEntity;
@Repository
public interface historyRepository extends JpaRepository<HistoriqueEntity, Long>{
	List<HistoriqueEntity> findByUser(String user);
}
