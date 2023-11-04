package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.Voiture;

public interface VoitureService {
	List<Voiture> retrieveAllVoitures();
	Voiture addVoiture (Voiture v);
	void deleteVoiture (Long id_proprietaire);
	Optional<Voiture> retrieveVoiture (Long id_proprietaire);
	Voiture updateVoiture(Voiture v);
}
