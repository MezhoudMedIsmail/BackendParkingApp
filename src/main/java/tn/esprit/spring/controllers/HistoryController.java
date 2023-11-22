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
//D�claration de la classe HistoryController qui agit comme un contr�leur dans une application Spring Boot.
public class HistoryController {

	// Injection de d�pendance de l'interface historyRepository.
	@Autowired
	private historyRepository historyRepository;

	// M�thode de gestion de la requ�te GET pour r�cup�rer l'ensemble de
	// l'historique.
	@GetMapping
	public ResponseEntity<List<HistoriqueEntity>> getHistorique() {
		// Appel de la m�thode findAll() de l'interface historyRepository pour obtenir
		// tous les �l�ments de l'historique.
		List<HistoriqueEntity> historique = historyRepository.findAll();
		// Retourne une r�ponse HTTP avec la liste d'historique et le code de statut OK
		// (200).
		return new ResponseEntity<List<HistoriqueEntity>>(historique, HttpStatus.OK);
	}

	// M�thode de gestion de la requ�te GET avec un chemin sp�cifique pour r�cup�rer
	// l'historique en fonction de l'ID de l'utilisateur.
	@GetMapping(path = "/{id}")
	public ResponseEntity<List<HistoriqueEntity>> getHistoriquePerId(@PathVariable String id) {
		// Appel de la m�thode findByUser() de l'interface historyRepository pour
		// obtenir l'historique sp�cifique � un utilisateur.
		List<HistoriqueEntity> historique = historyRepository.findByUser(id);

		// V�rifie si la liste d'historique n'est pas vide.
		if (!historique.isEmpty()) {
			// Retourne une r�ponse HTTP avec la liste d'historique et le code de statut OK
			// (200).
			return new ResponseEntity<List<HistoriqueEntity>>(historique, HttpStatus.OK);
		} else {
			// Retourne une r�ponse HTTP sans contenu (204) si la liste d'historique est
			// vide.
			return new ResponseEntity<List<HistoriqueEntity>>(historique, HttpStatus.NO_CONTENT);
		}
	}
}
