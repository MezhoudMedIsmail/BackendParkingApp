package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.Voiture;

//Interface d�finissant les op�rations de gestion des voitures.
public interface VoitureService {

	// M�thode pour r�cup�rer toutes les voitures.
	List<Voiture> retrieveAllVoitures();

	// M�thode pour ajouter une nouvelle voiture.
	Voiture addVoiture(Voiture v);

	// M�thode pour supprimer une voiture en fonction de l'ID du propri�taire.
	void deleteVoiture(Long id_proprietaire);

	// M�thode pour r�cup�rer une voiture en fonction de l'ID du propri�taire.
	Optional<Voiture> retrieveVoiture(Long id_proprietaire);

	// M�thode pour mettre � jour les informations d'une voiture.
	Voiture updateVoiture(Voiture v);
}
