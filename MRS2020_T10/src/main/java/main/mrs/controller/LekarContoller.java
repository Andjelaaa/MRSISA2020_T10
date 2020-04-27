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

import main.mrs.dto.LekarDTO;
import main.mrs.model.Lekar;
import main.mrs.model.TipPregleda;
import main.mrs.service.LekarService;
import main.mrs.service.TipPregledaService;

@RestController
@RequestMapping(value="api/lekar")
public class LekarContoller {
	
	@Autowired
	private LekarService LekarService;
	
	@Autowired
	TipPregledaService tps = new TipPregledaService();	

	@GetMapping(value = "/all")
	public ResponseEntity<List<LekarDTO>> getAllLekars() {

		List<Lekar> Lekars = LekarService.findAll();

		// convert Lekars to DTOs
		List<LekarDTO> LekarsDTO = new ArrayList<>();
		for (Lekar s : Lekars) {
			LekarsDTO.add(new LekarDTO(s));
		}

		return new ResponseEntity<>(LekarsDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<LekarDTO> saveLekar(@RequestBody LekarDTO LekarDTO) {

		Lekar Lekar = new Lekar();
		Lekar.setIme(LekarDTO.getIme());
		Lekar.setPrezime(LekarDTO.getPrezime());
		Lekar.setEmail(LekarDTO.getEmail());
		Lekar.setLozinka(LekarDTO.getLozinka());
		Lekar.setGrad(LekarDTO.getGrad());
		Lekar.setAdresa(LekarDTO.getAdresa());
		Lekar.setDrzava(LekarDTO.getDrzava());
		Lekar.setRvPocetak(LekarDTO.getRvPocetak());
		Lekar.setRvKraj(LekarDTO.getRvKraj());
		
		// proveriti da li moze ovako
		System.out.println(LekarDTO.getTipPregleda().getNaziv());
		
			
		TipPregleda tp= tps.findByNaziv(LekarDTO.getTipPregleda().getNaziv()); 
		Lekar.setTipPregleda(tp);
		
		// TODO: za kliniku staviti kliniku od ulogovanog administratora klinike
		//Lekar.setKlinika();
		
		try {
			Lekar = LekarService.save(Lekar);
		} catch (Exception e) {
			return new ResponseEntity<>(new LekarDTO(Lekar), HttpStatus.BAD_REQUEST);
		}


		return new ResponseEntity<>(new LekarDTO(Lekar), HttpStatus.CREATED);
	}
}
