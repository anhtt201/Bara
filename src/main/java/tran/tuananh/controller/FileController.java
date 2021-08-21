package tran.tuananh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tran.tuananh.exception.FileStorageException;
import tran.tuananh.model.File;
import tran.tuananh.payload.response.UploadFileResponse;
import tran.tuananh.repository.FileRepository;
import tran.tuananh.service.FileService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/fileManager")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileService fileService;

	@Autowired
	private FileRepository fileRepo;

	@PostMapping("/upload")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		File dbFile = fileService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/fileManager/downloadFile/").path(String.valueOf(dbFile.getId())).toUriString();

		return new UploadFileResponse(dbFile.getId(), dbFile.getFileName(), fileDownloadUri, file.getContentType(),
				file.getSize());
	}

	@PostMapping("/uploads")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
	}

	@PutMapping("/updateFile/{fileId}")
	public UploadFileResponse updateFile(@RequestParam("file") MultipartFile file, @PathVariable int fileId) {
		// Load file from database
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			File dbFile = fileService.getFile(fileId);

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/api/fileManager/downloadFile/").path(String.valueOf(fileId)).toUriString();

			dbFile.setFileName(fileName);
			dbFile.setFileType(file.getContentType());
			dbFile.setData(file.getBytes());

			fileRepo.save(dbFile);

			return new UploadFileResponse(dbFile.getId(), dbFile.getFileName(), fileDownloadUri, file.getContentType(),
					file.getSize());
		} catch (Exception e) {
			// TODO: handle exception
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
		}

	}

	@GetMapping("/getFile/{fileId}")
	public UploadFileResponse getFile(@PathVariable int fileId) {
		// Load file from database
		File dbFile = fileService.getFile(fileId);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/fileManager/downloadFile/").path(String.valueOf(fileId)).toUriString();

		return new UploadFileResponse(dbFile.getId(), dbFile.getFileName(), fileDownloadUri, dbFile.getFileType(),
				dbFile.getData().length);
	}

	@GetMapping("/getAll")
	public List<File> getAll() {

		return fileRepo.findAll();
	}

	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable int fileId) {
		// Load file from database
		File dbFile = fileService.getFile(fileId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));
	}

	@DeleteMapping("/deleteFile/{fileId}")
	public ResponseEntity<String> deleteFile(@PathVariable int fileId) {
		// Load file from database
		String res = fileService.deleteFile(fileId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

}
