package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.Parking;

//Interface d�finissant les op�rations de gestion des parkings.
public interface ParkingService {

	// M�thode pour r�cup�rer toutes les informations sur les parkings.
	List<Parking> retrieveAllParkings();

	// M�thode pour ajouter un nouveau parking.
	Parking addParking(Parking p);

	// M�thode pour supprimer un parking en fonction de son ID.
	void deleteParking(Long id);

	// M�thode pour r�cup�rer les informations d'un parking en fonction de son ID.
	Optional<Parking> retrieveParking(Long id);

	// M�thode pour mettre � jour les informations d'un parking en fonction de son
	// ID.
	Parking updateParking(Parking parking);
}
