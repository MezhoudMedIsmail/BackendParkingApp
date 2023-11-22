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
//D�claration de la classe ParkingController qui agit comme un contr�leur dans une application.
public class ParkingController {

	// Injection de d�pendance du service ParkingService.
	@Autowired
	ParkingService parkingService;

	// Injection de d�pendance du repository ParkingRepository.
	@Autowired
	ParkingRepository parkingRepository;

	// M�thode de gestion de la requ�te GET pour r�cup�rer une place de parking par
	// son identifiant.
	@GetMapping(path = "/{id}")
	public Parking getPlaceById(@PathVariable long id) {
		// Utilisation de la m�thode findById() du repository pour r�cup�rer la place de
		// parking par son identifiant.
		return parkingRepository.findById(id).get();
	}

	// M�thode de gestion de la requ�te POST pour ajouter une place de parking.
	@PostMapping("/add-parking")
	@ResponseBody
	Parking addParking(@RequestBody Parking p) {
		// Utilisation du service pour ajouter une place de parking.
		return parkingService.addParking(p);
	}

	// M�thode de gestion de la requ�te GET pour r�cup�rer toutes les places de
	// parking.
	@GetMapping("/retrieve-allParkings")
	@ResponseBody
	List<Parking> retrieveAllParkings() {
		// Utilisation du service pour r�cup�rer toutes les places de parking.
		return parkingService.retrieveAllParkings();
	}

	// M�thode de gestion de la requ�te PUT pour modifier une place de parking.
	@PutMapping("/modify-parking")
	@ResponseBody
	Parking updateParking(@RequestBody Parking p) {
		// Utilisation du service pour mettre � jour une place de parking.
		return parkingService.updateParking(p);
	}

	// M�thode de gestion de la requ�te DELETE pour supprimer une place de parking
	// par son identifiant.
	@DeleteMapping("delete-parking/{id}")
	void deleteParking(@PathVariable(name = "id") Long id) {
		// Utilisation du service pour supprimer une place de parking par son
		// identifiant.
		parkingService.deleteParking(id);
	}
}
