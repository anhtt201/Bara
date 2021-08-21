package tran.tuananh.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tran.tuananh.exception.FileStorageException;
import tran.tuananh.exception.MyFileNotFoundException;
import tran.tuananh.model.File;
import tran.tuananh.repository.FileRepository;

@Service
public class FileServiceImp implements FileService {

	@Autowired
	private FileRepository fileRepository;

	@Override
	public File storeFile(MultipartFile file) {
		// TODO Auto-generated method stub
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			File dbFile = new File(fileName, file.getContentType(), file.getBytes());

			return fileRepository.save(dbFile);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	@Override
	public File getFile(int fileId) {
		// TODO Auto-generated method stub
		return fileRepository.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}

	@Override
	public String deleteFile(int fileId) {
		// TODO Auto-generated method stub
		fileRepository.deleteById(fileId);
		return "Deleted";
	}
	
	

}
