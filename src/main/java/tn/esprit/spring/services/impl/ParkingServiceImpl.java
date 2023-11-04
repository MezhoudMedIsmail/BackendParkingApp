package tn.esprit.spring.services.impl;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Parking;
import tn.esprit.spring.repositories.ParkingRepository;
import tn.esprit.spring.services.ParkingService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ParkingServiceImpl implements ParkingService {
	@Autowired
	ParkingRepository parkingRepository;
	@Override
	public List<Parking> retrieveAllParkings() {
		return (List<Parking>) parkingRepository.findAll();
	}

	@Override
	public Parking addParking(Parking p) {
		 parkingRepository.save(p);
		 
		 return p;
	}

	@Override
	public void deleteParking(Long id) {
		parkingRepository.deleteById(id);		
	}

	@Override
	public Optional<Parking> retrieveParking(Long id) {
		return parkingRepository.findById(id) ;
	}

	@Override
	public Parking updateParking(Parking parking) {
		return parkingRepository.save(parking);
	}
}
