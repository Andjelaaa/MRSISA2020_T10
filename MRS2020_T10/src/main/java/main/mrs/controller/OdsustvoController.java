package main.mrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.mrs.dto.AdminKlinikeDTO;
import main.mrs.dto.KlinikaDTO;
import main.mrs.dto.OdsustvoDTO;
import main.mrs.model.AdminKlinike;
import main.mrs.model.Klinika;
import main.mrs.model.Odsustvo;
import main.mrs.model.Status;
import main.mrs.service.OdsustvoService;

@RestController
@RequestMapping(value="api/zahteviOdsustvo")
public class OdsustvoController {
	@Autowired
	private OdsustvoService OdsustvoService;
	
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<OdsustvoDTO> saveKlinika(@RequestBody OdsustvoDTO OdsustvoDTO) {
		
		Odsustvo zahtev = new Odsustvo();
		zahtev.setStatus(Status.zahtev);
		zahtev.setSestra(OdsustvoDTO.getSestra());
		zahtev.setLekar(OdsustvoDTO.getLekar());
		zahtev.setPocetak(OdsustvoDTO.getPocetak());
		zahtev.setKraj(OdsustvoDTO.getKraj());
		zahtev.setOpis(OdsustvoDTO.getOpis());
				
		try {
			zahtev = OdsustvoService.save(zahtev);
		} catch (Exception e) {
			return new ResponseEntity<>(new OdsustvoDTO(zahtev), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new OdsustvoDTO(zahtev), HttpStatus.CREATED);
	}
	
	
}
