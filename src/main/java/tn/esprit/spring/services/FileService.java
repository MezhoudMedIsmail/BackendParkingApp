package tn.esprit.spring.services;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.FileEntity;

//Interface définissant les opérations de gestion des fichiers.
public interface FileService {

	// Méthode pour stocker un fichier dans le système.
	FileEntity storeFile(MultipartFile file, String userId);

	// Méthode pour récupérer les informations d'un fichier en fonction de son ID.
	FileEntity getFile(String fileId);
}
