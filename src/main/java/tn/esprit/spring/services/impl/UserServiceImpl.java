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
//Impl�mentation de l'interface UserService d�finissant les op�rations de gestion des utilisateurs.
public class UserServiceImpl implements UserService {

	// Injection de d�pendances des repositories et services n�cessaires.
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

	// M�thode pour cr�er un nouvel utilisateur.
	@Override
	public UserDto createUser(UserDto user) {

		// V�rification si l'utilisateur existe d�j� dans la base de donn�es.
		UserEntity checkUser = userRepository.findByEmail(user.getEmail());

		if (checkUser != null) {
			throw new RuntimeException("L'utilisateur existe d�j�.");
		}

		// Cr�ation d'une nouvelle entit� utilisateur et copie des propri�t�s de l'objet
		// UserDto.
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		// Chiffrement du mot de passe.
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		// G�n�ration d'un identifiant unique pour l'utilisateur.
		userEntity.setUserId(util.generateUserId(32));

		// Sauvegarde de l'utilisateur dans la base de donn�es.
		UserEntity newUser = userRepository.save(userEntity);

		// Cr�ation d'un objet UserDto pour le renvoyer en r�ponse.
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(newUser, userDto);

		return userDto;
	}

	// M�thode pour charger les d�tails d'un utilisateur � partir de son adresse
	// e-mail.
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	// M�thode pour r�cup�rer un utilisateur � partir de son adresse e-mail.
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

	// M�thode pour r�cup�rer un utilisateur � partir de son identifiant unique.
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

	// M�thode pour mettre � jour les informations d'un utilisateur.
	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null) {
			throw new UsernameNotFoundException(userId);
		}

		// Mise � jour des propri�t�s de l'utilisateur.
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setHiring_date(userDto.getHiring_date());
		userEntity.setPhone_num(userDto.getPhone_num());
		userEntity.setPost_title(userDto.getPost_title());
		userEntity.setRole(userDto.getRole());
		userEntity.setVoitures(userDto.getVoitures());

		// Sauvegarde de l'utilisateur mis � jour dans la base de donn�es.
		UserEntity userUpdated = userRepository.save(userEntity);

		// Cr�ation d'un objet UserDto pour le renvoyer en r�ponse.
		UserDto user = new UserDto();
		BeanUtils.copyProperties(userUpdated, user);

		return user;
	}

	// M�thode pour r�cup�rer la liste de tous les utilisateurs.
	@Override
	public List<UserEntity> retrieveAllUsers() {
		return (List<UserEntity>) userRepository.findAll();
	}

	// M�thode pour supprimer un utilisateur et ses historiques correspondants.
	@Override
	public void deleteUser(Long id) {
		// R�cup�ration de l'utilisateur par son ID.
		UserEntity u = userRepository.findById(id).get();

		// Suppression de l'utilisateur de la base de donn�es.
		userRepository.deleteById(id);

		// Suppression des historiques associ�s � l'utilisateur.
		List<HistoriqueEntity> h = historyRepository.findAll();
		for (HistoriqueEntity historiqueEntity : h) {
			if (historiqueEntity.getUser().equals(u.getUserId())) {
				historyRepository.deleteById(historiqueEntity.getId());
			}
		}
	}

	@Override
	public List<PlaceParking> setUsersToplaceParkings(PlaceParkingBooking dates) {
		// R�cup�ration de tous les utilisateurs � partir du service UserService.
		List<UserEntity> users = userService.retrieveAllUsers();

		// Trie des utilisateurs en fonction de la logique sp�cifi�e dans le service
		// UserService.
		List<UserEntity> sortedUsers = userService.sortUsers(users);

		// R�cup�ration de toutes les places de parking � partir du repository.
		List<PlaceParking> places = placeParkingRepository.findAll();

		int j = 0;

		// Parcours des places de parking disponibles.
		for (int i = 0; i < places.size(); i++) {
			// V�rification si tous les utilisateurs tri�s ont �t� affect�s.
			if (sortedUsers.size() > j) {
				UserEntity user = places.get(i).getUser();

				// Attribution de la place de parking � un utilisateur si elle est disponible.
				if (user == null) {
					places.get(i).setUser(sortedUsers.get(j));
					places.get(i).setStartDate(dates.getStartDate());
					places.get(i).setEndDate(dates.getEndDate());
					j++;
				}
			}
		}

		// Sauvegarde des places de parking mises � jour dans la base de donn�es.
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
