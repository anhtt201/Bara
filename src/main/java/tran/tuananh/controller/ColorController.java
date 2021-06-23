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

import tran.tuananh.model.Color;
import tran.tuananh.service.ColorService;

@RestController
@RequestMapping(value = "/api/v2")
@CrossOrigin(origins = "*")
public class ColorController {

	@Autowired
	private ColorService service;

	@GetMapping(value = "/color")
	private ResponseEntity<List<Color>> getAllColor() {
		List<Color> listColor = service.getAllColor();
		return new ResponseEntity<List<Color>>(listColor, HttpStatus.OK);
	}

	@PostMapping(value = "/color")
	private ResponseEntity<Color> addColor(@RequestBody Color color) {
		Color colorAdd = service.addColor(color);
		return new ResponseEntity<Color>(colorAdd, HttpStatus.OK);
	}

	@GetMapping(value = "/color/{id}")
	private ResponseEntity<Color> getColorById(@PathVariable("id") int colorId) {
		Color color = service.getColorById(colorId);
		return new ResponseEntity<Color>(color, HttpStatus.OK);
	}

	@PutMapping(value = "/color/{id}")
	private ResponseEntity<Color> updateColor(@PathVariable("id") int colorId, @RequestBody Color color) {
		Color colorUpdate = service.updateColor(colorId, color);
		return new ResponseEntity<Color>(colorUpdate, HttpStatus.OK);
	}

	@DeleteMapping(value = "/color/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	private ResponseEntity<String> deleteColor(@PathVariable("id") int colorId) {
		String res = service.deleteColor(colorId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

}
