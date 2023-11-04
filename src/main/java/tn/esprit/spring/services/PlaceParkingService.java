package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.PlaceParking;
import tn.esprit.spring.entities.UserEntity;
import tn.esprit.spring.requests.PlaceParkingBooking;

public interface PlaceParkingService {
	List<PlaceParking> retrieveAllPlaceParkings();
	PlaceParking addPlaceParking (long id,PlaceParking pp);
	void deletePlaceParking (Long id);
	PlaceParking updatePlaceParking(long id,PlaceParking placeparking);
	Optional<PlaceParking> retrievePlaceParking(Long id);
	
	public List<PlaceParking> getReservedPlaces();
	public PlaceParking Reserver(Long placeId,PlaceParkingBooking dates);
	public void cancelReserver(Long placeId);
	public PlaceParking Book(Long placeId,String userId,PlaceParkingBooking dates);
	public List<PlaceParking> getBookedPlaces();
	public PlaceParking ReserverPersonne(Long placeId,PlaceParkingBooking dates,String userId);
}
