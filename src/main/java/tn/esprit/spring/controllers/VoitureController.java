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
public class VoitureController {
	@Autowired
	VoitureService voitureService;
	
	@PostMapping("/add-voiture")
	@ResponseBody
	Voiture addVoiture(@RequestBody Voiture v){
		return voitureService.addVoiture(v);
		
	}
	
	@GetMapping("/retrieve-allVoitures")
	@ResponseBody
	List<Voiture> retrieveAllVoitures()
	{
		return voitureService.retrieveAllVoitures();
	}
	
	@PutMapping("/Modify-voiture")
	@ResponseBody
	Voiture updateVoiture(@RequestBody Voiture v)
	{
		return voitureService.updateVoiture(v) ;
	}
	
	@DeleteMapping("delete-voiture/{id_proprietaire}")
	
	void deleteVoiture(@PathVariable(name="id_proprietaire")Long id_proprietaire)
	{
		voitureService.deleteVoiture(id_proprietaire);
	}
}
