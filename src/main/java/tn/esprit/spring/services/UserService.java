package tn.esprit.spring.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.spring.entities.PlaceParking;
import tn.esprit.spring.entities.UserEntity;
import tn.esprit.spring.requests.PlaceParkingBooking;
import tn.esprit.spring.shared.dto.UserDto;

//Interface définissant les opérations de gestion des utilisateurs.
public interface UserService extends UserDetailsService {

	// Méthode pour créer un nouvel utilisateur à partir d'un objet UserDto.
	UserDto createUser(UserDto userDto);

	// Méthode pour obtenir un objet UserDto en fonction de l'adresse e-mail.
	UserDto getUser(String email);

	// Méthode pour obtenir un objet UserDto en fonction de l'ID de l'utilisateur.
	UserDto getUserbyUserId(String userId);

	// Méthode pour mettre à jour les informations d'un utilisateur en fonction de
	// son ID.
	UserDto updateUser(String id, UserDto userDto);

	// Méthode pour récupérer la liste de tous les utilisateurs en tant qu'entités
	// UserEntity.
	List<UserEntity> retrieveAllUsers();

	// Méthode pour trier une liste d'entités UserEntity.
	List<UserEntity> sortUsers(List<UserEntity> users);

	// Méthode pour attribuer des utilisateurs à des places de parking en fonction
	// de dates spécifiques.
	List<PlaceParking> setUsersToplaceParkings(PlaceParkingBooking dates);

	// Méthode pour supprimer un utilisateur en fonction de son ID.
	void deleteUser(Long id);
}
