package main.mrs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.mrs.dto.DijagnozaDTO;
import main.mrs.model.Dijagnoza;
import main.mrs.service.DijagnozaService;

@RestController
@RequestMapping(value="api/dijagnoze")
public class DijagnozaController {

	@Autowired
	private DijagnozaService DijagnozaService;
	

	@GetMapping(value = "/all")
	public ResponseEntity<List<DijagnozaDTO>> getAllDijagnoze() {

		List<Dijagnoza> dijagnoze = DijagnozaService.findAll();

		List<DijagnozaDTO> DijagnozeeDTO = new ArrayList<>();
		for (Dijagnoza s : dijagnoze) {
			DijagnozeeDTO.add(new DijagnozaDTO(s));
		}

		return new ResponseEntity<>(DijagnozeeDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<DijagnozaDTO> saveLekove(@RequestBody DijagnozaDTO DijagnozaDTO) {

		Dijagnoza dijagnoza = new Dijagnoza();
		dijagnoza.setNaziv(DijagnozaDTO.getNaziv());
		dijagnoza.setSifra(DijagnozaDTO.getSifra());
		
		try {
			dijagnoza = DijagnozaService.save(dijagnoza);
		} catch (Exception e) {
			return new ResponseEntity<>(new DijagnozaDTO(dijagnoza), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new DijagnozaDTO(dijagnoza), HttpStatus.CREATED);
	}
}
