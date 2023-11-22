package tn.esprit.spring.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.FileEntity;

//D�claration de l'interface FileRepository, qui �tend JpaRepository pour l'entit� FileEntity.
public interface FileRepository extends JpaRepository<FileEntity, String> {

	// M�thode de recherche d'un objet FileEntity par l'ID de l'utilisateur.
	Optional<FileEntity> findByUserId(String userId);
}
