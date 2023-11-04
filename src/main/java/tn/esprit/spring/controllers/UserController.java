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
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

    @Autowired
    private PlaceParkingRepository placeParkingRepository;

	@Autowired
	private FileService dbFileStorageService;

	
	@GetMapping(path = "/{id}")
	public ResponseEntity <UserResponse> getUser(@PathVariable String  id) {
		
			UserDto userDto = userService.getUserbyUserId(id);
			
			UserResponse userResponse = new UserResponse();
			
			BeanUtils.copyProperties(userDto, userResponse);
			
			return new ResponseEntity<UserResponse>(userResponse ,HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		
		UserDto createUser = userService.createUser(userDto);
		
		UserResponse userResponse = new UserResponse();
		
		BeanUtils.copyProperties(createUser, userResponse);
		
		return new ResponseEntity<UserResponse>(userResponse ,HttpStatus.CREATED);
	}
	
	
	@PutMapping(path = "/{id}" )
	public ResponseEntity <UserResponse> updateUser(@PathVariable String  id , @RequestBody UserRequest userRequest) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		
		UserDto updateUser = userService.updateUser(id ,userDto);
		
		UserResponse userResponse = new UserResponse();
		
		BeanUtils.copyProperties(updateUser, userResponse);
		
		return  new ResponseEntity<UserResponse>(userResponse ,HttpStatus.ACCEPTED);
	} 
	
	
	
	@DeleteMapping("delete-user/{id}")
	void deleteUser(@PathVariable(name="id")Long id)
	{
		userService.deleteUser(id);
	}
	
	
	@GetMapping("/retrieve-allUsers")
	@ResponseBody
	List<UserEntity> retrieveAllUsers()
	{
		return userService.retrieveAllUsers();
	}
	
	
	@PostMapping("/users/{userId}/parking/{parkingId}")
    public UserEntity assignParkingToUser(@PathVariable Long userId, @PathVariable Long parkingId) {
		UserEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        PlaceParking parking = placeParkingRepository.findById(parkingId).orElseThrow(() -> new EntityNotFoundException("Parking not found"));        

        userRepository.save(user);
        placeParkingRepository.save(parking);

        return user;
    }

	
    @GetMapping("/sorted")
    public List<UserEntity> getSortedUsers() {
        List<UserEntity> users = userService.retrieveAllUsers(); // récupère tous les utilisateurs à partir du service
        List<UserEntity> sortedUsers = userService.sortUsers(users); // trie les utilisateurs
        return sortedUsers;
    }
	
	@PostMapping("/sortPlacewithusers")
	public List<PlaceParking> set(@RequestBody PlaceParkingBooking dates){
		List<PlaceParking> places =userService.setUsersToplaceParkings(dates);
		return places;
	}
	
	@PostMapping("/uploadFile/{userId}")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String userId) {
		FileEntity dbFile = dbFileStorageService.storeFile(file, userId);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(dbFile.getId()).toUriString();

		return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
	}

	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
		// Load file from database
		FileEntity dbFile = dbFileStorageService.getFile(fileId);
		 if (dbFile == null) {
		        // Return a response with status code 204 (No Content) if the file is not found
		        return ResponseEntity.noContent().build();
		    }
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));
	}
	
	
	@Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail() {
        // Send an example email
        emailService.sendSimpleEmail("imedattia1032@gmail.com", "Hello", "This is a test email.");
    	//emailService.sendEmailWithTemplate();
        return ResponseEntity.ok("Email sent!");
    }

	
}
