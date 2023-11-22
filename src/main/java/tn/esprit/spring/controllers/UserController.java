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
//D�claration de la classe UserController qui agit comme un contr�leur dans une application.
public class UserController {

	// Injection de d�pendance du service UserService.
	@Autowired
	private UserService userService;

	// Injection de d�pendance du repository UserRepository.
	@Autowired
	private UserRepository userRepository;

	// Injection de d�pendance du repository PlaceParkingRepository.
	@Autowired
	private PlaceParkingRepository placeParkingRepository;

	// Injection de d�pendance du service FileService.
	@Autowired
	private FileService dbFileStorageService;

	// Injection de d�pendance du service EmailService.
	@Autowired
	private EmailService emailService;

	// M�thode de gestion de la requ�te GET pour r�cup�rer un utilisateur par son
	// identifiant.
	@GetMapping(path = "/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		// Appel du service pour r�cup�rer un utilisateur par son identifiant.
		UserDto userDto = userService.getUserbyUserId(id);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	// M�thode de gestion de la requ�te POST pour cr�er un utilisateur.
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		// Cr�ation d'un objet UserDto � partir de la requ�te.
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		// Appel du service pour cr�er un utilisateur.
		UserDto createUser = userService.createUser(userDto);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(createUser, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}

	// M�thode de gestion de la requ�te PUT pour mettre � jour un utilisateur par
	// son identifiant.
	@PutMapping(path = "/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		// Appel du service pour mettre � jour un utilisateur.
		UserDto updateUser = userService.updateUser(id, userDto);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(updateUser, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}

	// M�thode de gestion de la requ�te DELETE pour supprimer un utilisateur par son
	// identifiant.
	@DeleteMapping("delete-user/{id}")
	void deleteUser(@PathVariable(name = "id") Long id) {
		// Appel du service pour supprimer un utilisateur.
		userService.deleteUser(id);
	}

	// M�thode de gestion de la requ�te GET pour r�cup�rer tous les utilisateurs.
	@GetMapping("/retrieve-allUsers")
	@ResponseBody
	List<UserEntity> retrieveAllUsers() {
		// Appel du service pour r�cup�rer tous les utilisateurs.
		return userService.retrieveAllUsers();
	}

	// M�thode de gestion de la requ�te POST pour attribuer un parking � un
	// utilisateur.
	@PostMapping("/users/{userId}/parking/{parkingId}")
	public UserEntity assignParkingToUser(@PathVariable Long userId, @PathVariable Long parkingId) {
		// R�cup�ration de l'utilisateur et du parking par leurs identifiants
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

	// M�thode de gestion de la requ�te GET pour r�cup�rer tous les utilisateurs
	// tri�s.
	@GetMapping("/sorted")
	public List<UserEntity> getSortedUsers() {
		// R�cup�ration de tous les utilisateurs depuis le service.
		List<UserEntity> users = userService.retrieveAllUsers();
		// Tri des utilisateurs.
		List<UserEntity> sortedUsers = userService.sortUsers(users);
		return sortedUsers;
	}

	// M�thode de gestion de la requ�te POST pour trier les places de parking avec
	// les utilisateurs.
	@PostMapping("/sortPlacewithusers")
	public List<PlaceParking> set(@RequestBody PlaceParkingBooking dates) {
		// Appel du service pour trier les places de parking avec les utilisateurs.
		List<PlaceParking> places = userService.setUsersToplaceParkings(dates);
		return places;
	}

	// M�thode de gestion de la requ�te POST pour t�l�verser un fichier associ� � un
	// utilisateur.
	@PostMapping("/uploadFile/{userId}")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String userId) {
		// Stockage du fichier et r�cup�ration des informations associ�es.
		FileEntity dbFile = dbFileStorageService.storeFile(file, userId);
		// Construction de l'URI de t�l�chargement du fichier.
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(dbFile.getId()).toUriString();
		// Cr�ation de la r�ponse contenant les informations du fichier t�l�vers�.
		return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
	}

	// M�thode de gestion de la requ�te GET pour t�l�charger un fichier par son
	// identifiant.
	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
		// R�cup�ration du fichier depuis la base de donn�es.
		FileEntity dbFile = dbFileStorageService.getFile(fileId);
		// V�rification si le fichier existe.
		if (dbFile == null) {
			// Retourne une r�ponse avec le code de statut 204 (No Content) si le fichier
			// n'est pas trouv�.
			return ResponseEntity.noContent().build();
		}
		// Retourne une r�ponse avec le fichier t�l�charg�.
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));
	}

	// M�thode de gestion de la requ�te POST pour envoyer un email.
	@PostMapping("/send-email")
	public ResponseEntity<String> sendEmail() {
		// Envoi d'un email de test.
		emailService.sendSimpleEmail("imedattia1032@gmail.com", "Hello", "This is a test email.");
		// Retourne une r�ponse avec le message indiquant que l'email a �t� envoy�.
		return ResponseEntity.ok("Email sent!");
	}

}
