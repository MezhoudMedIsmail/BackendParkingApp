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
//D�claration de la classe PlaceParkingController qui agit comme un contr�leur dans une application.
public class PlaceParkingController {

	// Injection de d�pendance du service PlaceParkingService.
	@Autowired
	PlaceParkingService placeparkingService;

	// Injection de d�pendance du repository PlaceParkingRepository.
	@Autowired
	PlaceParkingRepository placeParkingRepository;

	// Injection de d�pendance du service UserService.
	@Autowired
	UserService userService;

	// Injection de d�pendance du repository UserRepository.
	@Autowired
	UserRepository userRepository;

	// M�thode de gestion de la requ�te POST pour ajouter une place de parking � un
	// utilisateur sp�cifi�.
	@PostMapping("/add-placeparking/{id}")
	@ResponseBody
	PlaceParking addPlaceParking(@RequestBody PlaceParking pp, @PathVariable long id) {
		// Appel du service pour ajouter une place de parking.
		return placeparkingService.addPlaceParking(id, pp);
	}

	// M�thode de gestion de la requ�te GET pour r�cup�rer toutes les places de
	// parking.
	@GetMapping("/retrieve-AllPlaceParkings")
	@ResponseBody
	List<PlaceParking> retrieveAllPlaceParkings() {
		// Appel du service pour r�cup�rer toutes les places de parking.
		return placeparkingService.retrieveAllPlaceParkings();
	}

	// M�thode de gestion de la requ�te PUT pour mettre � jour une place de parking
	// par son identifiant.
	@PutMapping("/Modify-placeparking/{id}")
	@ResponseBody
	PlaceParking updatePlaceParking(@RequestBody PlaceParking pp, @PathVariable("id") long id) {
		// Appel du service pour mettre � jour une place de parking.
		return placeparkingService.updatePlaceParking(id, pp);
	}

	// M�thode de gestion de la requ�te GET pour r�cup�rer une place de parking par
	// son identifiant.
	@GetMapping(path = "/{id}")
	public PlaceParking getPlaceById(@PathVariable long id) {
		// Utilisation de la m�thode findById() du repository pour r�cup�rer la place de
		// parking par son identifiant.
		return placeParkingRepository.findById(id).get();
	}

	// M�thode de gestion de la requ�te DELETE pour supprimer une place de parking
	// par son identifiant.
	@DeleteMapping("delete-placeparking/{id}")
	void deletePlaceParking(@PathVariable(name = "id") Long id) {
		// Appel du service pour supprimer une place de parking.
		placeparkingService.deletePlaceParking(id);
	}

	// M�thode de gestion de la requ�te PATCH pour r�server une place de parking.
	@PatchMapping("book/{placeId}")
	public ResponseEntity<PlaceParking> book(@PathVariable Long placeId, @RequestBody PlaceParkingBooking dates,
			Principal principal) {
		// Appel du service pour r�server une place de parking.
		PlaceParking place = placeparkingService.Book(placeId, principal.getName(), dates);
		return new ResponseEntity<PlaceParking>(place, HttpStatus.ACCEPTED);
	}

	// M�thode de gestion de la requ�te PATCH pour marquer une place de parking
	// comme r�serv�e.
	@PatchMapping("reserver/{placeId}")
	public ResponseEntity<PlaceParking> reserver(@PathVariable Long placeId, @RequestBody PlaceParkingBooking dates) {
		// Appel du service pour marquer une place de parking comme r�serv�e.
		PlaceParking place = placeparkingService.Reserver(placeId, dates);
		return new ResponseEntity<PlaceParking>(place, HttpStatus.ACCEPTED);
	}

	// M�thode de gestion de la requ�te PATCH pour annuler la r�servation d'une
	// place de parking.
	@PatchMapping("cancelReserver/{placeId}")
	public ResponseEntity<Object> cancelReserver(@PathVariable Long placeId) {
		// Appel du service pour annuler la r�servation d'une place de parking.
		placeparkingService.cancelReserver(placeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/getBooked")
	public ResponseEntity<List<PlaceParking>> getBooked(){
		List<PlaceParking> placesBooked = placeparkingService.getBookedPlaces();
		return new ResponseEntity<List<PlaceParking>>(placesBooked,HttpStatus.OK);
	}

	// M�thode de gestion de la requ�te GET pour r�cup�rer toutes les places de
	// parking r�serv�es.
	@GetMapping("/getReserved")
	public ResponseEntity<List<PlaceParking>> getReserved() {
		// Appel du service pour r�cup�rer toutes les places de parking r�serv�es.
		List<PlaceParking> placesBooked = placeparkingService.getReservedPlaces();
		return new ResponseEntity<List<PlaceParking>>(placesBooked, HttpStatus.OK);
	}

	// M�thode de gestion de la requ�te PATCH pour r�server une place de parking
	// pour une personne sp�cifi�e.
	@PatchMapping("ReserverPersonne/{placeId}/{userId}")
	public ResponseEntity<PlaceParking> ReserverPersonne(@PathVariable Long placeId, @PathVariable String userId,
			@RequestBody PlaceParkingBooking dates) {
		// Appel du service pour r�server une place de parking pour une personne
		// sp�cifi�e.
		PlaceParking place = placeparkingService.ReserverPersonne(placeId, dates, userId);
		return new ResponseEntity<PlaceParking>(place, HttpStatus.ACCEPTED);
	}

}
