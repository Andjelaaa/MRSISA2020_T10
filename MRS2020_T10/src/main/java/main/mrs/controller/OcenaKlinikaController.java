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

import main.mrs.dto.OcenaKlinikaDTO;
import main.mrs.model.OcenaKlinika;
import main.mrs.service.OcenaKlinikaService;

@RestController
@RequestMapping(value="api/ocenaklinika")
public class OcenaKlinikaController {

	@Autowired
	private OcenaKlinikaService OcenaKlinikaService;
	

	@GetMapping(value = "/all")
	public ResponseEntity<List<OcenaKlinikaDTO>> getAllOcenaKlinika() {

		List<OcenaKlinika> ocene = OcenaKlinikaService.findAll();

		List<OcenaKlinikaDTO> OcenaKlinikasDTO = new ArrayList<>();
		for (OcenaKlinika o : ocene) {
			OcenaKlinikasDTO.add(new OcenaKlinikaDTO(o));
		}

		return new ResponseEntity<>(OcenaKlinikasDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<OcenaKlinikaDTO> saveOceneKlinika(@RequestBody OcenaKlinikaDTO OcenaDTO) {

		OcenaKlinika ocena = new OcenaKlinika();
		ocena.setKlinikaId(OcenaDTO.getKlinikaId());
		ocena.setPacijentId(OcenaDTO.getPacijentId());
		ocena.setOcena(OcenaDTO.getOcena());

		
		try {
			ocena = OcenaKlinikaService.save(ocena);
		} catch (Exception e) {
			return new ResponseEntity<>(new OcenaKlinikaDTO(ocena), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new OcenaKlinikaDTO(ocena), HttpStatus.CREATED);
	}
	
}
