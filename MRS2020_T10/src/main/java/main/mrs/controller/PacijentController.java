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

import main.mrs.dto.PacijentDTO;
import main.mrs.dto.SearchPacijent;
import main.mrs.model.Pacijent;
import main.mrs.service.PacijentService;

@RestController
@RequestMapping(value = "api/pacijent")
public class PacijentController {

	@Autowired
	private PacijentService pacijentService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<PacijentDTO>> getAllPacijents() {

		List<Pacijent> Pacijents = pacijentService.findAll();

		// convert Pacijents to DTOs
		List<PacijentDTO> PacijentsDTO = new ArrayList<>();
		for (Pacijent s : Pacijents) {
			PacijentsDTO.add(new PacijentDTO(s));
		}

		return new ResponseEntity<>(PacijentsDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<PacijentDTO> savePacijent(@RequestBody PacijentDTO PacijentDTO) {

		Pacijent Pacijent = new Pacijent();
		Pacijent.setIme(PacijentDTO.getIme());
		Pacijent.setPrezime(PacijentDTO.getPrezime());
		Pacijent.setEmail(PacijentDTO.getEmail());
		Pacijent.setLozinka(PacijentDTO.getLozinka());
		Pacijent.setGrad(PacijentDTO.getGrad());
		Pacijent.setAdresa(PacijentDTO.getAdresa());
		Pacijent.setDrzava(PacijentDTO.getDrzava());
		Pacijent.setLbo(PacijentDTO.getLbo());
		Pacijent.setKontakt(PacijentDTO.getKontakt());
	
		Pacijent = pacijentService.save(Pacijent);
		return new ResponseEntity<>(new PacijentDTO(Pacijent), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/search")
	public ResponseEntity<List<PacijentDTO>> getSearchLekars(@RequestBody SearchPacijent sp) {
		System.out.println(sp.getIme()+sp.getPrezime());
		List<Pacijent> pacijenti = pacijentService.findByImeAndPrezimeAndLbo(sp.getIme().toUpperCase(), sp.getPrezime().toUpperCase(), sp.getLbo().toUpperCase());

		// convert Lekars to DTOs
		List<PacijentDTO> pacijentiDTO = new ArrayList<>();
		for (Pacijent s : pacijenti) {
			pacijentiDTO.add(new PacijentDTO(s));
		}

		return new ResponseEntity<>(pacijentiDTO, HttpStatus.OK);
	}
	

}