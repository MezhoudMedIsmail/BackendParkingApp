package tn.esprit.spring.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.HistoriqueEntity;
import tn.esprit.spring.entities.PlaceParking;
import tn.esprit.spring.entities.UserEntity;
import tn.esprit.spring.entities.Voiture;
import tn.esprit.spring.repositories.PlaceParkingRepository;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.repositories.historyRepository;
import tn.esprit.spring.requests.PlaceParkingBooking;
import tn.esprit.spring.services.EmailService;
import tn.esprit.spring.services.PlaceParkingService;
import tn.esprit.spring.services.UserService;
import tn.esprit.spring.shared.Utils;
import tn.esprit.spring.shared.dto.UserDto;

@Service
//Implémentation de l'interface UserService définissant les opérations de gestion des utilisateurs.
public class UserServiceImpl implements UserService {

	// Injection de dépendances des repositories et services nécessaires.
	@Autowired
	UserRepository userRepository;

	@Autowired
	PlaceParkingRepository placeParkingRepository;

	@Autowired
	UserService userService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils util;

	@Autowired
	historyRepository historyRepository;

	@Autowired
	private EmailService emailService;

	// Méthode pour créer un nouvel utilisateur.
	@Override
	public UserDto createUser(UserDto user) {

		// Vérification si l'utilisateur existe déjà dans la base de données.
		UserEntity checkUser = userRepository.findByEmail(user.getEmail());

		if (checkUser != null) {
			throw new RuntimeException("L'utilisateur existe déjà.");
		}

		// Création d'une nouvelle entité utilisateur et copie des propriétés de l'objet
		// UserDto.
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		// Chiffrement du mot de passe.
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		// Génération d'un identifiant unique pour l'utilisateur.
		userEntity.setUserId(util.generateUserId(32));

		// Sauvegarde de l'utilisateur dans la base de données.
		UserEntity newUser = userRepository.save(userEntity);

		// Création d'un objet UserDto pour le renvoyer en réponse.
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(newUser, userDto);

		return userDto;
	}

	// Méthode pour charger les détails d'un utilisateur à partir de son adresse
	// e-mail.
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	// Méthode pour récupérer un utilisateur à partir de son adresse e-mail.
	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	// Méthode pour récupérer un utilisateur à partir de son identifiant unique.
	@Override
	public UserDto getUserbyUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null) {
			throw new UsernameNotFoundException(userId);
		}

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	// Méthode pour mettre à jour les informations d'un utilisateur.
	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null) {
			throw new UsernameNotFoundException(userId);
		}

		// Mise à jour des propriétés de l'utilisateur.
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setHiring_date(userDto.getHiring_date());
		userEntity.setPhone_num(userDto.getPhone_num());
		userEntity.setPost_title(userDto.getPost_title());
		userEntity.setRole(userDto.getRole());
		userEntity.setVoitures(userDto.getVoitures());

		// Sauvegarde de l'utilisateur mis à jour dans la base de données.
		UserEntity userUpdated = userRepository.save(userEntity);

		// Création d'un objet UserDto pour le renvoyer en réponse.
		UserDto user = new UserDto();
		BeanUtils.copyProperties(userUpdated, user);

		return user;
	}

	// Méthode pour récupérer la liste de tous les utilisateurs.
	@Override
	public List<UserEntity> retrieveAllUsers() {
		return (List<UserEntity>) userRepository.findAll();
	}

	// Méthode pour supprimer un utilisateur et ses historiques correspondants.
	@Override
	public void deleteUser(Long id) {
		// Récupération de l'utilisateur par son ID.
		UserEntity u = userRepository.findById(id).get();

		// Suppression de l'utilisateur de la base de données.
		userRepository.deleteById(id);

		// Suppression des historiques associés à l'utilisateur.
		List<HistoriqueEntity> h = historyRepository.findAll();
		for (HistoriqueEntity historiqueEntity : h) {
			if (historiqueEntity.getUser().equals(u.getUserId())) {
				historyRepository.deleteById(historiqueEntity.getId());
			}
		}
	}

	@Override
	public List<PlaceParking> setUsersToplaceParkings(PlaceParkingBooking dates) {
		// Récupération de tous les utilisateurs à partir du service UserService.
		List<UserEntity> users = userService.retrieveAllUsers();

		// Trie des utilisateurs en fonction de la logique spécifiée dans le service
		// UserService.
		List<UserEntity> sortedUsers = userService.sortUsers(users);

		// Récupération de toutes les places de parking à partir du repository.
		List<PlaceParking> places = placeParkingRepository.findAll();

		int j = 0;

		// Parcours des places de parking disponibles.
		for (int i = 0; i < places.size(); i++) {
			// Vérification si tous les utilisateurs triés ont été affectés.
			if (sortedUsers.size() > j) {
				UserEntity user = places.get(i).getUser();

				// Attribution de la place de parking à un utilisateur si elle est disponible.
				if (user == null) {
					places.get(i).setUser(sortedUsers.get(j));
					places.get(i).setStartDate(dates.getStartDate());
					places.get(i).setEndDate(dates.getEndDate());
					j++;
				}
			}
		}

		// Sauvegarde des places de parking mises à jour dans la base de données.
		return placeParkingRepository.saveAll(places);
	}

	public List<UserEntity> sortUsers(List<UserEntity> users) {

		// Trier les users en fonction de la date d'embauche
		Collections.sort(users, Comparator.comparing(UserEntity::getHiring_date));
		// Tri des utilisateurs en fonction du titre du poste
		Collections.sort(users, Comparator.comparing(UserEntity::getPost_title));
		return users;
	}

}
