package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.Parking;

//Interface définissant les opérations de gestion des parkings.
public interface ParkingService {

	// Méthode pour récupérer toutes les informations sur les parkings.
	List<Parking> retrieveAllParkings();

	// Méthode pour ajouter un nouveau parking.
	Parking addParking(Parking p);

	// Méthode pour supprimer un parking en fonction de son ID.
	void deleteParking(Long id);

	// Méthode pour récupérer les informations d'un parking en fonction de son ID.
	Optional<Parking> retrieveParking(Long id);

	// Méthode pour mettre à jour les informations d'un parking en fonction de son
	// ID.
	Parking updateParking(Parking parking);
}
