package tn.esprit.spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.PlaceParking;

@Repository
public interface PlaceParkingRepository extends JpaRepository<PlaceParking, Long>{
	
}
