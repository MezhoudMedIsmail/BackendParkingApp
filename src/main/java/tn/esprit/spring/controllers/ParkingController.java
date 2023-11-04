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
public class ParkingController {
	@Autowired
	ParkingService parkingService;
	@Autowired
	ParkingRepository parkingRepository;
	
	@GetMapping(path="/{id}")
	public Parking getPlaceById(@PathVariable long id) {
		return parkingRepository.findById(id).get();
	}
	
	
	
	@PostMapping("/add-parking")
	@ResponseBody
	Parking addParking(@RequestBody Parking p){
		return parkingService.addParking(p);
		
	}
	
	@GetMapping("/retrieve-allParkings")
	@ResponseBody
	List<Parking> retrieveAllParkings()
	{
		return parkingService.retrieveAllParkings();
	}
	
	@PutMapping("/modify-parking")
	@ResponseBody
	Parking updateParking(@RequestBody Parking p)
	{
		return parkingService.updateParking(p) ;
	}
	
@DeleteMapping("delete-parking/{id}")
	
	void deleteParking(@PathVariable(name="id")Long id)
	{
		parkingService.deleteParking(id);
	}
}
