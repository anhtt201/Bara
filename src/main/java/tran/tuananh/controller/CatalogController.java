package tran.tuananh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tran.tuananh.model.Catalog;
import tran.tuananh.service.CatalogService;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "*")
public class CatalogController {

	@Autowired
	private CatalogService service;
		
	@Autowired
	private HttpServletRequest request;

	@GetMapping(value = "/catalog")
	private ResponseEntity<List<Catalog>> getAllCatalog() {
		List<Catalog> listCat = service.getAllCatalog();
		return new ResponseEntity<List<Catalog>>(listCat, HttpStatus.OK);
	}

	@PostMapping(value = "/catalog")
	private ResponseEntity<Catalog> addCatalog(@RequestBody Catalog catalog) {
		Catalog cat = service.addCatalog(catalog);
		return new ResponseEntity<Catalog>(cat, HttpStatus.OK);
	}

	@GetMapping(value = "/catalog/{id}")
	private ResponseEntity<Catalog> getCatalogById(@PathVariable("id") int catId) {
		Catalog cat = service.getCatalogById(catId);
		return new ResponseEntity<Catalog>(cat, HttpStatus.OK);
	}

	@PutMapping(value = "/catalog/{id}")
	private ResponseEntity<Catalog> updateCatalog(@PathVariable("id") int catId, @RequestBody Catalog catalog) {
		Catalog cat = service.updateCatalog(catId, catalog);
		return new ResponseEntity<Catalog>(cat, HttpStatus.OK);
	}

	@DeleteMapping(value = "/catalog/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	private ResponseEntity<String> deleteCatalog(@PathVariable("id") int catId) {
		String res = service.deleteCatalog(catId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

	@DeleteMapping(value = "/catalog")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	private ResponseEntity<String> deleteAllCatalog() {
		String res = service.deleteAllCatalog();
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	
}
