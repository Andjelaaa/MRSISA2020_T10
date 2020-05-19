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

import main.mrs.dto.LekDTO;
import main.mrs.dto.OcenaLekarDTO;
import main.mrs.model.Lek;
import main.mrs.model.OcenaLekar;
import main.mrs.model.PomocnaKlasa2;
import main.mrs.service.OcenaLekarService;

@RestController
@RequestMapping(value="api/ocenalekar")
public class OcenaLekarController {

	
	@Autowired
	private OcenaLekarService OcenaLekarService;
	

	@GetMapping(value = "/all")
	public ResponseEntity<List<OcenaLekarDTO>> getAllOcenaLekar() {

		List<OcenaLekar> ocene = OcenaLekarService.findAll();

		List<OcenaLekarDTO> OcenaLekarsDTO = new ArrayList<>();
		for (OcenaLekar o : ocene) {
			OcenaLekarsDTO.add(new OcenaLekarDTO(o));
		}

		return new ResponseEntity<>(OcenaLekarsDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<OcenaLekarDTO> saveOceneLekar(@RequestBody OcenaLekarDTO OcenaDTO) {

		OcenaLekar ocena = new OcenaLekar();
		ocena.setLekarId(OcenaDTO.getLekarId());
		ocena.setPacijentId(OcenaDTO.getPacijentId());
		ocena.setOcena(OcenaDTO.getOcena());

		
		try {
			ocena = OcenaLekarService.save(ocena);
		} catch (Exception e) {
			return new ResponseEntity<>(new OcenaLekarDTO(ocena), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new OcenaLekarDTO(ocena), HttpStatus.CREATED);
	}
	
}
