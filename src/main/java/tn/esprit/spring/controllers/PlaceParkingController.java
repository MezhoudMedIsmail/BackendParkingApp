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
public class PlaceParkingController {
	@Autowired
	PlaceParkingService placeparkingService;
	
	@Autowired
	PlaceParkingRepository placeParkingRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/add-placeparking/{id}")
	@ResponseBody
	PlaceParking addPlaceParking(@RequestBody PlaceParking pp,@PathVariable long id){
		return placeparkingService.addPlaceParking(id,pp);
		
	}
	
	
	@GetMapping("/retrieve-AllPlaceParkings")
	@ResponseBody
	List<PlaceParking>  retrieveAllPlaceParkings()
	{
		return placeparkingService.retrieveAllPlaceParkings();
	}
	
	
	@PutMapping("/Modify-placeparking/{id}")
	@ResponseBody
	PlaceParking updatePlaceParking(@RequestBody PlaceParking pp,@PathVariable("id") long id)
	{
		return placeparkingService.updatePlaceParking(id,pp) ;
	}
	
	
	@GetMapping(path="/{id}")
	public PlaceParking getPlaceById(@PathVariable long id) {
		return placeParkingRepository.findById(id).get();
	}
	
	@DeleteMapping("delete-placeparking/{id}")
	void deletePlaceParking(@PathVariable(name="id")Long id)
	{
		placeparkingService.deletePlaceParking(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PatchMapping("book/{placeId}")
	public ResponseEntity<PlaceParking> book(@PathVariable Long placeId,@RequestBody PlaceParkingBooking dates,Principal principal){
		PlaceParking place = placeparkingService.Book(placeId,principal.getName(),dates); 
		return new ResponseEntity<PlaceParking>(place,HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("reserver/{placeId}")
	public ResponseEntity<PlaceParking> reserver(@PathVariable Long placeId,@RequestBody PlaceParkingBooking dates){
		PlaceParking place = placeparkingService.Reserver(placeId,dates); 
		return new ResponseEntity<PlaceParking>(place,HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("cancelReserver/{placeId}")
	public ResponseEntity<Object> cancelReserver(@PathVariable Long placeId){
		placeparkingService.cancelReserver(placeId); 
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/getBooked")
	public ResponseEntity<List<PlaceParking>> getBooked(){
		List<PlaceParking> placesBooked = placeparkingService.getBookedPlaces();
		return new ResponseEntity<List<PlaceParking>>(placesBooked,HttpStatus.OK);
	}
	
	@GetMapping("/getReserved")
	public ResponseEntity<List<PlaceParking>> getReserved(){
		List<PlaceParking> placesBooked = placeparkingService.getReservedPlaces();
		return new ResponseEntity<List<PlaceParking>>(placesBooked,HttpStatus.OK);
	}
	@PatchMapping("ReserverPersonne/{placeId}/{userId}")
	public ResponseEntity<PlaceParking> ReserverPersonne(@PathVariable Long placeId,@PathVariable String userId,@RequestBody PlaceParkingBooking dates){
		PlaceParking place = placeparkingService.ReserverPersonne(placeId,dates,userId); 
		return new ResponseEntity<PlaceParking>(place,HttpStatus.ACCEPTED);
	}
	
	
	
}
