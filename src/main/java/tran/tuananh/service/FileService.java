package tran.tuananh.service;

import org.springframework.web.multipart.MultipartFile;

import tran.tuananh.model.File;

public interface FileService {

	public File storeFile(MultipartFile file);

	public File getFile(int fileId);
	
	public String deleteFile(int fileId);
}