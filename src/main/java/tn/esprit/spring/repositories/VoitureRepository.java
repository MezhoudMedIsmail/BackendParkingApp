package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Voiture;

@Repository
//D�claration de l'interface VoitureRepository, qui �tend CrudRepository pour l'entit� Voiture.
public interface VoitureRepository extends CrudRepository<Voiture, Long> {

}
