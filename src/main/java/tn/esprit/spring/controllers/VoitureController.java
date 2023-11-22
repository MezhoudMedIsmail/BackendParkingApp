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

import tn.esprit.spring.entities.Voiture;
import tn.esprit.spring.services.VoitureService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
//Déclaration de la classe VoitureController qui agit comme un contrôleur dans une application.
public class VoitureController {

	// Injection de dépendance du service VoitureService.
	@Autowired
	VoitureService voitureService;

	// Méthode de gestion de la requête POST pour ajouter une voiture.
	@PostMapping("/add-voiture")
	@ResponseBody
	Voiture addVoiture(@RequestBody Voiture v) {
		// Utilisation du service pour ajouter une voiture.
		return voitureService.addVoiture(v);
	}

	// Méthode de gestion de la requête GET pour récupérer toutes les voitures.
	@GetMapping("/retrieve-allVoitures")
	@ResponseBody
	List<Voiture> retrieveAllVoitures() {
		// Utilisation du service pour récupérer toutes les voitures.
		return voitureService.retrieveAllVoitures();
	}

	// Méthode de gestion de la requête PUT pour modifier une voiture.
	@PutMapping("/Modify-voiture")
	@ResponseBody
	Voiture updateVoiture(@RequestBody Voiture v) {
		// Utilisation du service pour mettre à jour une voiture.
		return voitureService.updateVoiture(v);
	}

	// Méthode de gestion de la requête DELETE pour supprimer une voiture par l'ID
	// du propriétaire.
	@DeleteMapping("delete-voiture/{id_proprietaire}")
	void deleteVoiture(@PathVariable(name = "id_proprietaire") Long id_proprietaire) {
		// Utilisation du service pour supprimer une voiture par l'ID du propriétaire.
		voitureService.deleteVoiture(id_proprietaire);
	}
}
