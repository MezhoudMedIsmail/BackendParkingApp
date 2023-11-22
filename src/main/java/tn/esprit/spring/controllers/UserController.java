package tn.esprit.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import tn.esprit.spring.entities.FileEntity;
import tn.esprit.spring.entities.PlaceParking;
import tn.esprit.spring.entities.UserEntity;
import tn.esprit.spring.entities.Voiture;
import tn.esprit.spring.repositories.PlaceParkingRepository;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.requests.PlaceParkingBooking;
import tn.esprit.spring.requests.UserRequest;
import tn.esprit.spring.responses.UploadFileResponse;
import tn.esprit.spring.responses.UserAssignResponse;
import tn.esprit.spring.responses.UserResponse;
import tn.esprit.spring.services.EmailService;
import tn.esprit.spring.services.FileService;
import tn.esprit.spring.services.PlaceParkingService;
import tn.esprit.spring.services.UserService;
import tn.esprit.spring.shared.dto.UserDto;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
//Déclaration de la classe UserController qui agit comme un contrôleur dans une application.
public class UserController {

	// Injection de dépendance du service UserService.
	@Autowired
	private UserService userService;

	// Injection de dépendance du repository UserRepository.
	@Autowired
	private UserRepository userRepository;

	// Injection de dépendance du repository PlaceParkingRepository.
	@Autowired
	private PlaceParkingRepository placeParkingRepository;

	// Injection de dépendance du service FileService.
	@Autowired
	private FileService dbFileStorageService;

	// Injection de dépendance du service EmailService.
	@Autowired
	private EmailService emailService;

	// Méthode de gestion de la requête GET pour récupérer un utilisateur par son
	// identifiant.
	@GetMapping(path = "/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		// Appel du service pour récupérer un utilisateur par son identifiant.
		UserDto userDto = userService.getUserbyUserId(id);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	// Méthode de gestion de la requête POST pour créer un utilisateur.
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		// Création d'un objet UserDto à partir de la requête.
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		// Appel du service pour créer un utilisateur.
		UserDto createUser = userService.createUser(userDto);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(createUser, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}

	// Méthode de gestion de la requête PUT pour mettre à jour un utilisateur par
	// son identifiant.
	@PutMapping(path = "/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		// Appel du service pour mettre à jour un utilisateur.
		UserDto updateUser = userService.updateUser(id, userDto);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(updateUser, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}

	// Méthode de gestion de la requête DELETE pour supprimer un utilisateur par son
	// identifiant.
	@DeleteMapping("delete-user/{id}")
	void deleteUser(@PathVariable(name = "id") Long id) {
		// Appel du service pour supprimer un utilisateur.
		userService.deleteUser(id);
	}

	// Méthode de gestion de la requête GET pour récupérer tous les utilisateurs.
	@GetMapping("/retrieve-allUsers")
	@ResponseBody
	List<UserEntity> retrieveAllUsers() {
		// Appel du service pour récupérer tous les utilisateurs.
		return userService.retrieveAllUsers();
	}

	// Méthode de gestion de la requête POST pour attribuer un parking à un
	// utilisateur.
	@PostMapping("/users/{userId}/parking/{parkingId}")
	public UserEntity assignParkingToUser(@PathVariable Long userId, @PathVariable Long parkingId) {
		// Récupération de l'utilisateur et du parking par leurs identifiants
		// respectifs.
		UserEntity user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User not found"));
		PlaceParking parking = placeParkingRepository.findById(parkingId)
				.orElseThrow(() -> new EntityNotFoundException("Parking not found"));
		// Sauvegarde de l'utilisateur et du parking.
		userRepository.save(user);
		placeParkingRepository.save(parking);
		return user;
	}

	// Méthode de gestion de la requête GET pour récupérer tous les utilisateurs
	// triés.
	@GetMapping("/sorted")
	public List<UserEntity> getSortedUsers() {
		// Récupération de tous les utilisateurs depuis le service.
		List<UserEntity> users = userService.retrieveAllUsers();
		// Tri des utilisateurs.
		List<UserEntity> sortedUsers = userService.sortUsers(users);
		return sortedUsers;
	}

	// Méthode de gestion de la requête POST pour trier les places de parking avec
	// les utilisateurs.
	@PostMapping("/sortPlacewithusers")
	public List<PlaceParking> set(@RequestBody PlaceParkingBooking dates) {
		// Appel du service pour trier les places de parking avec les utilisateurs.
		List<PlaceParking> places = userService.setUsersToplaceParkings(dates);
		return places;
	}

	// Méthode de gestion de la requête POST pour téléverser un fichier associé à un
	// utilisateur.
	@PostMapping("/uploadFile/{userId}")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String userId) {
		// Stockage du fichier et récupération des informations associées.
		FileEntity dbFile = dbFileStorageService.storeFile(file, userId);
		// Construction de l'URI de téléchargement du fichier.
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(dbFile.getId()).toUriString();
		// Création de la réponse contenant les informations du fichier téléversé.
		return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
	}

	// Méthode de gestion de la requête GET pour télécharger un fichier par son
	// identifiant.
	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
		// Récupération du fichier depuis la base de données.
		FileEntity dbFile = dbFileStorageService.getFile(fileId);
		// Vérification si le fichier existe.
		if (dbFile == null) {
			// Retourne une réponse avec le code de statut 204 (No Content) si le fichier
			// n'est pas trouvé.
			return ResponseEntity.noContent().build();
		}
		// Retourne une réponse avec le fichier téléchargé.
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));
	}

	// Méthode de gestion de la requête POST pour envoyer un email.
	@PostMapping("/send-email")
	public ResponseEntity<String> sendEmail() {
		// Envoi d'un email de test.
		emailService.sendSimpleEmail("imedattia1032@gmail.com", "Hello", "This is a test email.");
		// Retourne une réponse avec le message indiquant que l'email a été envoyé.
		return ResponseEntity.ok("Email sent!");
	}

}
