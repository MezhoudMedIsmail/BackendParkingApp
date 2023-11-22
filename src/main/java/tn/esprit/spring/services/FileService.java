package tn.esprit.spring.services;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.FileEntity;

//Interface d�finissant les op�rations de gestion des fichiers.
public interface FileService {

	// M�thode pour stocker un fichier dans le syst�me.
	FileEntity storeFile(MultipartFile file, String userId);

	// M�thode pour r�cup�rer les informations d'un fichier en fonction de son ID.
	FileEntity getFile(String fileId);
}
