package main.mrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.mrs.dto.LekarDTO;
import main.mrs.dto.OdsustvoDTO;
import main.mrs.model.Lekar;
import main.mrs.model.MedSestra;
import main.mrs.model.Odsustvo;
import main.mrs.model.Status;
import main.mrs.service.LekarService;
import main.mrs.service.MedSestraService;
import main.mrs.service.OdsustvoService;;
@RestController
@RequestMapping(value="api/zahteviodsustvo")
public class OdsustvoController {
	
	@Autowired
	private OdsustvoService OdsustvoService;
	
	@Autowired
	private MedSestraService MedSestraService;
	
	@Autowired
	private LekarService LekarService;
	
	@PostMapping(consumes = "application/json", value= "/{email}")
	public ResponseEntity<OdsustvoDTO> saveOdsustvo(@RequestBody OdsustvoDTO OdsustvoDTO, @PathVariable String email) {
	
		Odsustvo zahtev = new Odsustvo();
		zahtev.setTip(OdsustvoDTO.getTip());
		zahtev.setStatus(Status.zahtev);
		zahtev.setPocetak(OdsustvoDTO.getPocetak());
		zahtev.setKraj(OdsustvoDTO.getKraj());
        zahtev.setOpis(OdsustvoDTO.getOpis());
		MedSestra m = MedSestraService.findByEmail(email);
		
		if(m != null) {
			zahtev.setSestra(m);
			m.getOdsustvo().add(zahtev);
			zahtev.setLekar(new Lekar());
		}
		
		Lekar l = LekarService.findByEmail(email);
		
		if(l != null) {
			zahtev.setLekar(l);
			l.getOdsustvo().add(zahtev);
			zahtev.setSestra(new MedSestra());
		}
		
		try {
			zahtev = OdsustvoService.save(zahtev);
		} catch (Exception e) {
			return new ResponseEntity<>(new OdsustvoDTO(zahtev), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new OdsustvoDTO(zahtev), HttpStatus.CREATED);
	}
	
	
}
