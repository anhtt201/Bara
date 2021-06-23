package tran.tuananh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tran.tuananh.model.Size;
import tran.tuananh.service.SizeService;

@RestController
@RequestMapping(value = "/api/v2")
@CrossOrigin(origins = "*")
public class SizeController {

	@Autowired
	private SizeService service;

	@GetMapping(value = "/size")
	private ResponseEntity<List<Size>> getAllSize() {
		List<Size> listSize = service.getAllSize();
		return new ResponseEntity<List<Size>>(listSize, HttpStatus.OK);
	}

	@PostMapping(value = "/size")
	private ResponseEntity<Size> addSize(@RequestBody Size size) {
		Size sizeAdd = service.addSize(size);
		return new ResponseEntity<Size>(sizeAdd, HttpStatus.OK);
	}

	@GetMapping(value = "/size/{id}")
	private ResponseEntity<Size> getSizeById(@PathVariable("id") int sizeId) {
		Size size = service.getSizeById(sizeId);
		return new ResponseEntity<Size>(size, HttpStatus.OK);
	}

	@PutMapping(value = "/size/{id}")
	private ResponseEntity<Size> updateSize(@PathVariable("id") int sizeId, @RequestBody Size size) {
		Size sizeUpdate = service.updateSize(sizeId, size);
		return new ResponseEntity<Size>(sizeUpdate, HttpStatus.OK);
	}

	@DeleteMapping(value = "/size/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	private ResponseEntity<String> deleteSize(@PathVariable("id") int sizeId) {
		String res = service.deleteSize(sizeId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
}
