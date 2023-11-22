package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.UserEntity;

@Repository
//D�claration de l'interface UserRepository, qui �tend CrudRepository pour l'entit� UserEntity.
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	// M�thode de recherche d'un objet UserEntity par son adresse email.
	UserEntity findByEmail(String email);

	// M�thode de recherche d'un objet UserEntity par son identifiant d'utilisateur.
	UserEntity findByUserId(String userId);
}
