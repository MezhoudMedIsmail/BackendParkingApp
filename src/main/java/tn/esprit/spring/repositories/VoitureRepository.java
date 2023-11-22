package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Voiture;

@Repository
//Déclaration de l'interface VoitureRepository, qui étend CrudRepository pour l'entité Voiture.
public interface VoitureRepository extends CrudRepository<Voiture, Long> {

}
