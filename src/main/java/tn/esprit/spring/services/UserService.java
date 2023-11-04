package tn.esprit.spring.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.spring.entities.PlaceParking;
import tn.esprit.spring.entities.UserEntity;
import tn.esprit.spring.requests.PlaceParkingBooking;
import tn.esprit.spring.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	UserDto createUser(UserDto userDto);
	
	UserDto getUser(String email);
	
	UserDto getUserbyUserId(String userId);
	
	UserDto updateUser(String id ,UserDto userDto);
	
	//void deleteUser(String  userId);
	
	List<UserEntity>retrieveAllUsers();
	
	public List<UserEntity> sortUsers(List<UserEntity> users);
	public List<PlaceParking> setUsersToplaceParkings(PlaceParkingBooking dates);

	void deleteUser(Long id);
	
	//List<UserEntity> assignUsersToPlaces(List<UserEntity> users, List<PlaceParking> places);
}
