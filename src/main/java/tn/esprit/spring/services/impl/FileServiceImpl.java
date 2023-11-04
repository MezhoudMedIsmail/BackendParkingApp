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
public class FileServiceImpl implements FileService{
	
	@Autowired
    private FileRepository dbFileRepository;

	@Override
	public FileEntity storeFile(MultipartFile file,String userId) {
		Optional<FileEntity> fileUser = dbFileRepository.findByUserId(userId);
		if(fileUser.isPresent()) {
			dbFileRepository.delete(fileUser.get());
		}
		 // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            FileEntity dbFile = new FileEntity(fileName, file.getContentType(), file.getBytes(),userId);
            
            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
	}

	@Override
	public FileEntity getFile(String userId) {
		return dbFileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Oops! We couldn't find your profile picture, but we're working to bring it back soon! Stay tuned!",
                        new FileNotFoundException("File not found with id " + userId)));
	}

}
