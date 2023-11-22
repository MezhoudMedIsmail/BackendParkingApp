package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.HistoriqueEntity;
import tn.esprit.spring.repositories.historyRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/History")
//Déclaration de la classe HistoryController qui agit comme un contrôleur dans une application Spring Boot.
public class HistoryController {

	// Injection de dépendance de l'interface historyRepository.
	@Autowired
	private historyRepository historyRepository;

	// Méthode de gestion de la requête GET pour récupérer l'ensemble de
	// l'historique.
	@GetMapping
	public ResponseEntity<List<HistoriqueEntity>> getHistorique() {
		// Appel de la méthode findAll() de l'interface historyRepository pour obtenir
		// tous les éléments de l'historique.
		List<HistoriqueEntity> historique = historyRepository.findAll();
		// Retourne une réponse HTTP avec la liste d'historique et le code de statut OK
		// (200).
		return new ResponseEntity<List<HistoriqueEntity>>(historique, HttpStatus.OK);
	}

	// Méthode de gestion de la requête GET avec un chemin spécifique pour récupérer
	// l'historique en fonction de l'ID de l'utilisateur.
	@GetMapping(path = "/{id}")
	public ResponseEntity<List<HistoriqueEntity>> getHistoriquePerId(@PathVariable String id) {
		// Appel de la méthode findByUser() de l'interface historyRepository pour
		// obtenir l'historique spécifique à un utilisateur.
		List<HistoriqueEntity> historique = historyRepository.findByUser(id);

		// Vérifie si la liste d'historique n'est pas vide.
		if (!historique.isEmpty()) {
			// Retourne une réponse HTTP avec la liste d'historique et le code de statut OK
			// (200).
			return new ResponseEntity<List<HistoriqueEntity>>(historique, HttpStatus.OK);
		} else {
			// Retourne une réponse HTTP sans contenu (204) si la liste d'historique est
			// vide.
			return new ResponseEntity<List<HistoriqueEntity>>(historique, HttpStatus.NO_CONTENT);
		}
	}
}
