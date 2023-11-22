package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.Voiture;

//Interface définissant les opérations de gestion des voitures.
public interface VoitureService {

	// Méthode pour récupérer toutes les voitures.
	List<Voiture> retrieveAllVoitures();

	// Méthode pour ajouter une nouvelle voiture.
	Voiture addVoiture(Voiture v);

	// Méthode pour supprimer une voiture en fonction de l'ID du propriétaire.
	void deleteVoiture(Long id_proprietaire);

	// Méthode pour récupérer une voiture en fonction de l'ID du propriétaire.
	Optional<Voiture> retrieveVoiture(Long id_proprietaire);

	// Méthode pour mettre à jour les informations d'une voiture.
	Voiture updateVoiture(Voiture v);
}
