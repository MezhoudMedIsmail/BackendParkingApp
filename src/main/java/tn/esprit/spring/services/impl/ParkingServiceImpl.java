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
//Impl�mentation de l'interface ParkingService d�finissant les op�rations de gestion des parkings.
public class ParkingServiceImpl implements ParkingService {

	// Injection de d�pendance du repository de parkings.
	@Autowired
	ParkingRepository parkingRepository;

	// M�thode pour r�cup�rer toutes les informations sur les parkings.
	@Override
	public List<Parking> retrieveAllParkings() {
		return (List<Parking>) parkingRepository.findAll();
	}

	// M�thode pour ajouter un nouveau parking.
	@Override
	public Parking addParking(Parking p) {
		parkingRepository.save(p);
		return p;
	}

	// M�thode pour supprimer un parking en fonction de son ID.
	@Override
	public void deleteParking(Long id) {
		parkingRepository.deleteById(id);
	}

	// M�thode pour r�cup�rer les informations d'un parking en fonction de son ID.
	@Override
	public Optional<Parking> retrieveParking(Long id) {
		return parkingRepository.findById(id);
	}

	// M�thode pour mettre � jour les informations d'un parking.
	@Override
	public Parking updateParking(Parking parking) {
		return parkingRepository.save(parking);
	}
}
