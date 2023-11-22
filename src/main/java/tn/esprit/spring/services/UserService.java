package tn.esprit.spring.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.spring.entities.PlaceParking;
import tn.esprit.spring.entities.UserEntity;
import tn.esprit.spring.requests.PlaceParkingBooking;
import tn.esprit.spring.shared.dto.UserDto;

//Interface d�finissant les op�rations de gestion des utilisateurs.
public interface UserService extends UserDetailsService {

	// M�thode pour cr�er un nouvel utilisateur � partir d'un objet UserDto.
	UserDto createUser(UserDto userDto);

	// M�thode pour obtenir un objet UserDto en fonction de l'adresse e-mail.
	UserDto getUser(String email);

	// M�thode pour obtenir un objet UserDto en fonction de l'ID de l'utilisateur.
	UserDto getUserbyUserId(String userId);

	// M�thode pour mettre � jour les informations d'un utilisateur en fonction de
	// son ID.
	UserDto updateUser(String id, UserDto userDto);

	// M�thode pour r�cup�rer la liste de tous les utilisateurs en tant qu'entit�s
	// UserEntity.
	List<UserEntity> retrieveAllUsers();

	// M�thode pour trier une liste d'entit�s UserEntity.
	List<UserEntity> sortUsers(List<UserEntity> users);

	// M�thode pour attribuer des utilisateurs � des places de parking en fonction
	// de dates sp�cifiques.
	List<PlaceParking> setUsersToplaceParkings(PlaceParkingBooking dates);

	// M�thode pour supprimer un utilisateur en fonction de son ID.
	void deleteUser(Long id);
}
