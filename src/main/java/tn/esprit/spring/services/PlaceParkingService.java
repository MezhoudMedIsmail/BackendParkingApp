package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.PlaceParking;
import tn.esprit.spring.entities.UserEntity;
import tn.esprit.spring.requests.PlaceParkingBooking;

//Interface d�finissant les op�rations de gestion des places de parking.
public interface PlaceParkingService {

	// M�thode pour r�cup�rer toutes les places de parking.
	List<PlaceParking> retrieveAllPlaceParkings();

	// M�thode pour ajouter une nouvelle place de parking associ�e � un utilisateur
	// sp�cifique.
	PlaceParking addPlaceParking(long id, PlaceParking pp);

	// M�thode pour supprimer une place de parking en fonction de son ID.
	void deletePlaceParking(Long id);

	// M�thode pour mettre � jour les informations d'une place de parking en
	// fonction de son ID.
	PlaceParking updatePlaceParking(long id, PlaceParking placeparking);

	// M�thode pour r�cup�rer une place de parking en fonction de son ID.
	Optional<PlaceParking> retrievePlaceParking(Long id);

	// M�thode pour obtenir la liste des places de parking r�serv�es.
	List<PlaceParking> getReservedPlaces();

	// M�thode pour r�server une place de parking en fonction de son ID et des dates
	// sp�cifi�es.
	PlaceParking Reserver(Long placeId, PlaceParkingBooking dates);

	// M�thode pour annuler la r�servation d'une place de parking en fonction de son
	// ID.
	void cancelReserver(Long placeId);

	// M�thode pour r�server une place de parking pour un utilisateur sp�cifique en
	// fonction de son ID, des dates et de l'ID de l'utilisateur.
	PlaceParking Book(Long placeId, String userId, PlaceParkingBooking dates);

	// M�thode pour obtenir la liste des places de parking r�serv�es.
	List<PlaceParking> getBookedPlaces();

	// M�thode pour r�server une place de parking pour une personne sp�cifique en
	// fonction de son ID, des dates et de l'ID de la personne.
	PlaceParking ReserverPersonne(Long placeId, PlaceParkingBooking dates, String userId);
}
