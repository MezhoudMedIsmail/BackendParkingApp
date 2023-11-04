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

import tn.esprit.spring.entities.PlaceParking;
import tn.esprit.spring.entities.UserEntity;
import tn.esprit.spring.entities.Voiture;
import tn.esprit.spring.repositories.PlaceParkingRepository;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.requests.PlaceParkingBooking;
import tn.esprit.spring.services.EmailService;
import tn.esprit.spring.services.PlaceParkingService;
import tn.esprit.spring.services.UserService;
import tn.esprit.spring.shared.Utils;
import tn.esprit.spring.shared.dto.UserDto;
@Service
public class UserServiceImpl implements UserService {
	
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
    private EmailService emailService;

	
	@Override
	public UserDto createUser(UserDto user) {
		
		UserEntity checkUser = userRepository.findByEmail(user.getEmail());
		
		if (checkUser != null) throw new RuntimeException("user alreaddy exists");  
		
		UserEntity userEntity = new UserEntity();
		
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		userEntity.setUserId(util.generateUserId(32)); 
		   
		UserEntity newUser = userRepository.save(userEntity);
		
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(newUser, userDto);
		
		return userDto;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			
		UserEntity userEntity = userRepository.findByEmail(email);
			if(userEntity == null ) throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword() , new ArrayList<>());
	}
	
	@Override
	public UserDto getUser(String email) {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null ) throw new UsernameNotFoundException(email);
		
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto ;
	}


	@Override
	public UserDto getUserbyUserId(String userId) {
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null ) throw new UsernameNotFoundException(userId);
		
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto;
	}

	
	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null ) throw new UsernameNotFoundException(userId);
		
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setHiring_date(userDto.getHiring_date());
		userEntity.setPhone_num(userDto.getPhone_num());
		userEntity.setPost_title(userDto.getPost_title());
		userEntity.setRole(userDto.getRole());
		userEntity.setVoitures(userDto.getVoitures());
		
			
		UserEntity userUpdated = 	userRepository.save(userEntity);
			
			UserDto user = new UserDto();
			
			BeanUtils.copyProperties(userUpdated, user);
			
		return user;
	}
	
	@Override
	public List<UserEntity> retrieveAllUsers() {
		
		return (List<UserEntity>) userRepository.findAll();
	}
	
	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
	}
	
	@Override
	public List<PlaceParking> setUsersToplaceParkings(PlaceParkingBooking dates) {
		List<UserEntity> users = userService.retrieveAllUsers(); // récupère tous les utilisateurs à partir du service
        List<UserEntity> sortedUsers = userService.sortUsers(users); // trie les utilisateurs
        List<PlaceParking> places = placeParkingRepository.findAll();
        int j = 0;
        for (int i = 0; i < places.size(); i++) {
        	if(sortedUsers.size()> j) {
        		UserEntity user = places.get(i).getUser();
                if (user == null) {
                	places.get(i).setUser(sortedUsers.get(j));
                	places.get(i).setStartDate(dates.getStartDate());
                	places.get(i).setEndDate(dates.getEndDate());
                	j++;
                }	
        	}
		}
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
