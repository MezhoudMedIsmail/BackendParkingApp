package tn.esprit.spring.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, String>{
	Optional<FileEntity> findByUserId(String userId);
}
