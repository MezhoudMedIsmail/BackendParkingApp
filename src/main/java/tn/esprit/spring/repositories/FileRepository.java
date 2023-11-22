package tn.esprit.spring.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.FileEntity;

//Déclaration de l'interface FileRepository, qui étend JpaRepository pour l'entité FileEntity.
public interface FileRepository extends JpaRepository<FileEntity, String> {

	// Méthode de recherche d'un objet FileEntity par l'ID de l'utilisateur.
	Optional<FileEntity> findByUserId(String userId);
}
