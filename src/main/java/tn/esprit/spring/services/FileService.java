package tn.esprit.spring.services;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.FileEntity;


public interface FileService {
	  FileEntity storeFile(MultipartFile file,String userId);
	  FileEntity getFile(String fileId);
}
