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
public class HistoryController {
	@Autowired
	private historyRepository historyRepository;
	
	@GetMapping
	public ResponseEntity <List<HistoriqueEntity>> getHistorique() {
		
			List<HistoriqueEntity> historique = historyRepository.findAll();			
			return new ResponseEntity<List<HistoriqueEntity>>(historique ,HttpStatus.OK);
	}
	@GetMapping(path = "/{id}")
	public ResponseEntity <List<HistoriqueEntity>> getHistoriquePerId(@PathVariable String id) {
			
			List<HistoriqueEntity> historique = historyRepository.findByUser(id);			
			return !historique.isEmpty() ? new ResponseEntity<List<HistoriqueEntity>>(historique ,HttpStatus.OK) : new ResponseEntity<List<HistoriqueEntity>>(historique ,HttpStatus.NO_CONTENT);
	}
}
