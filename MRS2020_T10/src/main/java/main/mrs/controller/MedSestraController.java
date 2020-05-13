package main.mrs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.mrs.dto.LekarDTO;
import main.mrs.dto.MedSestraDTO;
import main.mrs.dto.SearchLekar;
import main.mrs.model.Lekar;
import main.mrs.model.MedSestra;
import main.mrs.service.AutoritetService;
import main.mrs.service.MedSestraService;
import main.mrs.service.PacijentService;


@RestController
@RequestMapping(value="api/medsestraa")
public class MedSestraController {
	@Autowired
	private MedSestraService MedSestraService;
	
	@Autowired
	private PacijentService PacijentService;
	@Autowired
	private AutoritetService autoritetService;
	

	@GetMapping(value = "/all")
	public ResponseEntity<List<MedSestraDTO>> getAllMedSestrs() {

		List<MedSestra> sestre =  MedSestraService.findAll();

		List<MedSestraDTO> medsDTO = new ArrayList<>();
		for (MedSestra s : sestre) {
			medsDTO.add(new MedSestraDTO(s));
		}

		return new ResponseEntity<>(medsDTO, HttpStatus.OK);
	}

	 @Transactional
	@PostMapping(consumes = "application/json")
	public ResponseEntity<MedSestraDTO> saveLekar(@RequestBody MedSestraDTO MedSestraDTO) {

		MedSestra m = new MedSestra();
		m.setIme(MedSestraDTO.getIme());
		m.setPrezime(MedSestraDTO.getPrezime());
		m.setEmail(MedSestraDTO.getEmail());
		m.setLozinka(PacijentService.encodePassword(MedSestraDTO.getLozinka()));
		m.setGrad(MedSestraDTO.getGrad());
		m.setAdresa(MedSestraDTO.getAdresa());
		m.setDrzava(MedSestraDTO.getDrzava());
		m.setKontakt(MedSestraDTO.getKontakt());
		m.setRadvr_pocetak(MedSestraDTO.getRadvr_pocetak());
		m.setRadvr_kraj(MedSestraDTO.getRadvr_kraj());		
		
		m.setAutoriteti(autoritetService.findByName("ROLE_MED_SESTRA"));
		
		// TODO: za kliniku staviti kliniku od ulogovanog administratora klinike
		//m.setKlinika();
		
		try {
			m = MedSestraService.save(m);
		} catch (Exception e) {
			return new ResponseEntity<>(new MedSestraDTO(m), HttpStatus.BAD_REQUEST);
		}


		return new ResponseEntity<>(new MedSestraDTO(m), HttpStatus.CREATED);
	
	 	}
		 
}
