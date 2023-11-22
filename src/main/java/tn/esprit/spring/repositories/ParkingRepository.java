package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Parking;

@Repository
public interface ParkingRepository extends CrudRepository<Parking, Long> {

}
