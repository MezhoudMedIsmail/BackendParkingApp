package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Parking;
import tn.esprit.spring.repositories.ParkingRepository;
import tn.esprit.spring.services.ParkingService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/parkings")
//Déclaration de la classe ParkingController qui agit comme un contrôleur dans une application.
public class ParkingController {

	// Injection de dépendance du service ParkingService.
	@Autowired
	ParkingService parkingService;

	// Injection de dépendance du repository ParkingRepository.
	@Autowired
	ParkingRepository parkingRepository;

	// Méthode de gestion de la requête GET pour récupérer une place de parking par
	// son identifiant.
	@GetMapping(path = "/{id}")
	public Parking getPlaceById(@PathVariable long id) {
		// Utilisation de la méthode findById() du repository pour récupérer la place de
		// parking par son identifiant.
		return parkingRepository.findById(id).get();
	}

	// Méthode de gestion de la requête POST pour ajouter une place de parking.
	@PostMapping("/add-parking")
	@ResponseBody
	Parking addParking(@RequestBody Parking p) {
		// Utilisation du service pour ajouter une place de parking.
		return parkingService.addParking(p);
	}

	// Méthode de gestion de la requête GET pour récupérer toutes les places de
	// parking.
	@GetMapping("/retrieve-allParkings")
	@ResponseBody
	List<Parking> retrieveAllParkings() {
		// Utilisation du service pour récupérer toutes les places de parking.
		return parkingService.retrieveAllParkings();
	}

	// Méthode de gestion de la requête PUT pour modifier une place de parking.
	@PutMapping("/modify-parking")
	@ResponseBody
	Parking updateParking(@RequestBody Parking p) {
		// Utilisation du service pour mettre à jour une place de parking.
		return parkingService.updateParking(p);
	}

	// Méthode de gestion de la requête DELETE pour supprimer une place de parking
	// par son identifiant.
	@DeleteMapping("delete-parking/{id}")
	void deleteParking(@PathVariable(name = "id") Long id) {
		// Utilisation du service pour supprimer une place de parking par son
		// identifiant.
		parkingService.deleteParking(id);
	}
}
