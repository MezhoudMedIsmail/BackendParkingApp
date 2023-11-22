package tn.esprit.spring.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.PlaceParking;
import tn.esprit.spring.entities.UserEntity;
import tn.esprit.spring.repositories.PlaceParkingRepository;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.requests.PlaceParkingBooking;
import tn.esprit.spring.services.PlaceParkingService;
import tn.esprit.spring.services.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/placeparkings")
//Déclaration de la classe PlaceParkingController qui agit comme un contrôleur dans une application.
public class PlaceParkingController {

	// Injection de dépendance du service PlaceParkingService.
	@Autowired
	PlaceParkingService placeparkingService;

	// Injection de dépendance du repository PlaceParkingRepository.
	@Autowired
	PlaceParkingRepository placeParkingRepository;

	// Injection de dépendance du service UserService.
	@Autowired
	UserService userService;

	// Injection de dépendance du repository UserRepository.
	@Autowired
	UserRepository userRepository;

	// Méthode de gestion de la requête POST pour ajouter une place de parking à un
	// utilisateur spécifié.
	@PostMapping("/add-placeparking/{id}")
	@ResponseBody
	PlaceParking addPlaceParking(@RequestBody PlaceParking pp, @PathVariable long id) {
		// Appel du service pour ajouter une place de parking.
		return placeparkingService.addPlaceParking(id, pp);
	}

	// Méthode de gestion de la requête GET pour récupérer toutes les places de
	// parking.
	@GetMapping("/retrieve-AllPlaceParkings")
	@ResponseBody
	List<PlaceParking> retrieveAllPlaceParkings() {
		// Appel du service pour récupérer toutes les places de parking.
		return placeparkingService.retrieveAllPlaceParkings();
	}

	// Méthode de gestion de la requête PUT pour mettre à jour une place de parking
	// par son identifiant.
	@PutMapping("/Modify-placeparking/{id}")
	@ResponseBody
	PlaceParking updatePlaceParking(@RequestBody PlaceParking pp, @PathVariable("id") long id) {
		// Appel du service pour mettre à jour une place de parking.
		return placeparkingService.updatePlaceParking(id, pp);
	}

	// Méthode de gestion de la requête GET pour récupérer une place de parking par
	// son identifiant.
	@GetMapping(path = "/{id}")
	public PlaceParking getPlaceById(@PathVariable long id) {
		// Utilisation de la méthode findById() du repository pour récupérer la place de
		// parking par son identifiant.
		return placeParkingRepository.findById(id).get();
	}

	// Méthode de gestion de la requête DELETE pour supprimer une place de parking
	// par son identifiant.
	@DeleteMapping("delete-placeparking/{id}")
	void deletePlaceParking(@PathVariable(name = "id") Long id) {
		// Appel du service pour supprimer une place de parking.
		placeparkingService.deletePlaceParking(id);
	}

	// Méthode de gestion de la requête PATCH pour réserver une place de parking.
	@PatchMapping("book/{placeId}")
	public ResponseEntity<PlaceParking> book(@PathVariable Long placeId, @RequestBody PlaceParkingBooking dates,
			Principal principal) {
		// Appel du service pour réserver une place de parking.
		PlaceParking place = placeparkingService.Book(placeId, principal.getName(), dates);
		return new ResponseEntity<PlaceParking>(place, HttpStatus.ACCEPTED);
	}

	// Méthode de gestion de la requête PATCH pour marquer une place de parking
	// comme réservée.
	@PatchMapping("reserver/{placeId}")
	public ResponseEntity<PlaceParking> reserver(@PathVariable Long placeId, @RequestBody PlaceParkingBooking dates) {
		// Appel du service pour marquer une place de parking comme réservée.
		PlaceParking place = placeparkingService.Reserver(placeId, dates);
		return new ResponseEntity<PlaceParking>(place, HttpStatus.ACCEPTED);
	}

	// Méthode de gestion de la requête PATCH pour annuler la réservation d'une
	// place de parking.
	@PatchMapping("cancelReserver/{placeId}")
	public ResponseEntity<Object> cancelReserver(@PathVariable Long placeId) {
		// Appel du service pour annuler la réservation d'une place de parking.
		placeparkingService.cancelReserver(placeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/getBooked")
	public ResponseEntity<List<PlaceParking>> getBooked(){
		List<PlaceParking> placesBooked = placeparkingService.getBookedPlaces();
		return new ResponseEntity<List<PlaceParking>>(placesBooked,HttpStatus.OK);
	}

	// Méthode de gestion de la requête GET pour récupérer toutes les places de
	// parking réservées.
	@GetMapping("/getReserved")
	public ResponseEntity<List<PlaceParking>> getReserved() {
		// Appel du service pour récupérer toutes les places de parking réservées.
		List<PlaceParking> placesBooked = placeparkingService.getReservedPlaces();
		return new ResponseEntity<List<PlaceParking>>(placesBooked, HttpStatus.OK);
	}

	// Méthode de gestion de la requête PATCH pour réserver une place de parking
	// pour une personne spécifiée.
	@PatchMapping("ReserverPersonne/{placeId}/{userId}")
	public ResponseEntity<PlaceParking> ReserverPersonne(@PathVariable Long placeId, @PathVariable String userId,
			@RequestBody PlaceParkingBooking dates) {
		// Appel du service pour réserver une place de parking pour une personne
		// spécifiée.
		PlaceParking place = placeparkingService.ReserverPersonne(placeId, dates, userId);
		return new ResponseEntity<PlaceParking>(place, HttpStatus.ACCEPTED);
	}

}
