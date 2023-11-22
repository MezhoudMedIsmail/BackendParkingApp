package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.UserEntity;

@Repository
//Déclaration de l'interface UserRepository, qui étend CrudRepository pour l'entité UserEntity.
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	// Méthode de recherche d'un objet UserEntity par son adresse email.
	UserEntity findByEmail(String email);

	// Méthode de recherche d'un objet UserEntity par son identifiant d'utilisateur.
	UserEntity findByUserId(String userId);
}
