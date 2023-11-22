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
//Implémentation de l'interface ParkingService définissant les opérations de gestion des parkings.
public class ParkingServiceImpl implements ParkingService {

	// Injection de dépendance du repository de parkings.
	@Autowired
	ParkingRepository parkingRepository;

	// Méthode pour récupérer toutes les informations sur les parkings.
	@Override
	public List<Parking> retrieveAllParkings() {
		return (List<Parking>) parkingRepository.findAll();
	}

	// Méthode pour ajouter un nouveau parking.
	@Override
	public Parking addParking(Parking p) {
		parkingRepository.save(p);
		return p;
	}

	// Méthode pour supprimer un parking en fonction de son ID.
	@Override
	public void deleteParking(Long id) {
		parkingRepository.deleteById(id);
	}

	// Méthode pour récupérer les informations d'un parking en fonction de son ID.
	@Override
	public Optional<Parking> retrieveParking(Long id) {
		return parkingRepository.findById(id);
	}

	// Méthode pour mettre à jour les informations d'un parking.
	@Override
	public Parking updateParking(Parking parking) {
		return parkingRepository.save(parking);
	}
}
