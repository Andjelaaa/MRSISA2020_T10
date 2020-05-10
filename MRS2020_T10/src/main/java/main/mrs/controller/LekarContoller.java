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
import main.mrs.dto.SearchLekar;
import main.mrs.model.Lekar;
import main.mrs.model.TipPregleda;
import main.mrs.service.AutoritetService;
import main.mrs.service.LekarService;
import main.mrs.service.PacijentService;
import main.mrs.service.TipPregledaService;

@RestController
@RequestMapping(value="api/lekar")
public class LekarContoller {
	
	@Autowired
	private LekarService LekarService;
	
	@Autowired
	private PacijentService PacijentService;
	@Autowired
	private AutoritetService autoritetService;
	
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

	 @Transactional
	@PostMapping(consumes = "application/json")
	public ResponseEntity<LekarDTO> saveLekar(@RequestBody LekarDTO LekarDTO) {

		Lekar Lekar = new Lekar();
		Lekar.setIme(LekarDTO.getIme());
		Lekar.setPrezime(LekarDTO.getPrezime());
		Lekar.setEmail(LekarDTO.getEmail());
		Lekar.setLozinka(PacijentService.encodePassword(LekarDTO.getLozinka()));
		Lekar.setGrad(LekarDTO.getGrad());
		Lekar.setAdresa(LekarDTO.getAdresa());
		Lekar.setDrzava(LekarDTO.getDrzava());
		Lekar.setRvPocetak(LekarDTO.getRvPocetak());
		Lekar.setRvKraj(LekarDTO.getRvKraj());		
		Lekar.setKontakt(LekarDTO.getKontakt());
		Lekar.setProsecnaOcena(0.0);
		Lekar.setBrojOcena(0);
		TipPregleda tp= tps.findByNaziv(LekarDTO.getTipPregleda().getNaziv()); 
		Lekar.setTipPregleda(tp);
		Lekar.setAutoriteti(autoritetService.findByName("ROLE_LEKAR"));
		
		// TODO: za kliniku staviti kliniku od ulogovanog administratora klinike
		//Lekar.setKlinika();
		
		try {
			Lekar = LekarService.save(Lekar);
		} catch (Exception e) {
			return new ResponseEntity<>(new LekarDTO(Lekar), HttpStatus.BAD_REQUEST);
		}


		return new ResponseEntity<>(new LekarDTO(Lekar), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/search")
	public ResponseEntity<List<LekarDTO>> getSearchLekars(@RequestBody SearchLekar sl) {
		System.out.println(sl.getIme()+sl.getPrezime());
		List<Lekar> Lekars = LekarService.findByImeAndPrezime(sl.getIme().toUpperCase(), sl.getPrezime().toUpperCase());

		// convert Lekars to DTOs
		List<LekarDTO> LekarsDTO = new ArrayList<>();
		for (Lekar s : Lekars) {
			LekarsDTO.add(new LekarDTO(s));
		}

		return new ResponseEntity<>(LekarsDTO, HttpStatus.OK);
	}
	
	@Transactional // obavezno ova anotacija, inace puca
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteLekar(@PathVariable Integer id) {
		Lekar Lekar = LekarService.findOne(id);

		if (Lekar != null) {
			// Provera da li je lekar ima zakazane preglede
			if(!Lekar.getPregled().isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			LekarService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
