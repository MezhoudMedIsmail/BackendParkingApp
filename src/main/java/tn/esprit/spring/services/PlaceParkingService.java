package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.PlaceParking;
import tn.esprit.spring.entities.UserEntity;
import tn.esprit.spring.requests.PlaceParkingBooking;

//Interface définissant les opérations de gestion des places de parking.
public interface PlaceParkingService {

	// Méthode pour récupérer toutes les places de parking.
	List<PlaceParking> retrieveAllPlaceParkings();

	// Méthode pour ajouter une nouvelle place de parking associée à un utilisateur
	// spécifique.
	PlaceParking addPlaceParking(long id, PlaceParking pp);

	// Méthode pour supprimer une place de parking en fonction de son ID.
	void deletePlaceParking(Long id);

	// Méthode pour mettre à jour les informations d'une place de parking en
	// fonction de son ID.
	PlaceParking updatePlaceParking(long id, PlaceParking placeparking);

	// Méthode pour récupérer une place de parking en fonction de son ID.
	Optional<PlaceParking> retrievePlaceParking(Long id);

	// Méthode pour obtenir la liste des places de parking réservées.
	List<PlaceParking> getReservedPlaces();

	// Méthode pour réserver une place de parking en fonction de son ID et des dates
	// spécifiées.
	PlaceParking Reserver(Long placeId, PlaceParkingBooking dates);

	// Méthode pour annuler la réservation d'une place de parking en fonction de son
	// ID.
	void cancelReserver(Long placeId);

	// Méthode pour réserver une place de parking pour un utilisateur spécifique en
	// fonction de son ID, des dates et de l'ID de l'utilisateur.
	PlaceParking Book(Long placeId, String userId, PlaceParkingBooking dates);

	// Méthode pour obtenir la liste des places de parking réservées.
	List<PlaceParking> getBookedPlaces();

	// Méthode pour réserver une place de parking pour une personne spécifique en
	// fonction de son ID, des dates et de l'ID de la personne.
	PlaceParking ReserverPersonne(Long placeId, PlaceParkingBooking dates, String userId);
}
