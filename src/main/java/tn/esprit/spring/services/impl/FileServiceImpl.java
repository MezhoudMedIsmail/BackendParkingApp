package tn.esprit.spring.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import tn.esprit.spring.entities.FileEntity;
import tn.esprit.spring.repositories.FileRepository;
import tn.esprit.spring.services.FileService;
import tn.esprit.spring.shared.FileStorageException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
//Impl�mentation de l'interface FileService d�finissant les op�rations de gestion des fichiers.
public class FileServiceImpl implements FileService {

	// Injection de d�pendance du repository de fichiers.
	@Autowired
	private FileRepository dbFileRepository;

	// M�thode pour stocker un fichier dans le syst�me.
	@Override
	public FileEntity storeFile(MultipartFile file, String userId) {
		// Recherche d'un fichier existant associ� � l'utilisateur.
		Optional<FileEntity> fileUser = dbFileRepository.findByUserId(userId);

		// Suppression du fichier existant s'il est trouv�.
		if (fileUser.isPresent()) {
			dbFileRepository.delete(fileUser.get());
		}

		// Normalisation du nom de fichier.
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// V�rification si le nom de fichier contient des caract�res invalides.
			if (fileName.contains("..")) {
				throw new FileStorageException(
						"D�sol� ! Le nom de fichier contient une s�quence de chemin non valide " + fileName);
			}

			// Cr�ation d'un objet FileEntity et sauvegarde dans le repository.
			FileEntity dbFile = new FileEntity(fileName, file.getContentType(), file.getBytes(), userId);
			return dbFileRepository.save(dbFile);
		} catch (IOException ex) {
			throw new FileStorageException("Impossible de stocker le fichier " + fileName + ". Veuillez r�essayer !",
					ex);
		}
	}

	// M�thode pour r�cup�rer les informations d'un fichier en fonction de l'ID de
	// l'utilisateur.
	@Override
	public FileEntity getFile(String userId) {
		// Recherche du fichier associ� � l'ID de l'utilisateur.
		return dbFileRepository.findByUserId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Oops ! Nous n'avons pas pu trouver votre photo de profil, mais nous travaillons pour la ramener bient�t ! Restez � l'�coute !",
				new FileNotFoundException("Fichier non trouv� avec l'ID " + userId)));
	}
}
