package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Voiture;
@Repository
public interface VoitureRepository extends CrudRepository<Voiture, Long> {

}
