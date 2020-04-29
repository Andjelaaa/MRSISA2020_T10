package main.mrs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.mrs.dto.PregledDTO;
import main.mrs.model.Lekar;
import main.mrs.model.Pacijent;
import main.mrs.model.Pregled;
import main.mrs.model.Sala;
import main.mrs.model.Status;
import main.mrs.model.TipPregleda;
import main.mrs.service.LekarService;
import main.mrs.service.PacijentService;
import main.mrs.service.PregledService;
import main.mrs.service.SalaService;
import main.mrs.service.TipPregledaService;

@RestController
@RequestMapping(value="api/pregled")
public class PregledController {
	@Autowired
	private PregledService PregledService;
	@Autowired
	private LekarService LekarService;
	@Autowired
	private SalaService SalaService;
	@Autowired
	private TipPregledaService TipPregledaService;
	
	@Autowired
	private PacijentService PacijentService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<PregledDTO>> getAllPregleds() {

		List<Pregled> Pregleds = PregledService.findAll();

		// convert Pregleds to DTOs
		List<PregledDTO> PregledsDTO = new ArrayList<>();
		for (Pregled s : Pregleds) {
			PregledsDTO.add(new PregledDTO(s));
		}

		return new ResponseEntity<>(PregledsDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/{pregledId}/{pacijentId}")
	public ResponseEntity<PregledDTO> zakaziPregled(@PathVariable long pregledId, @PathVariable int pacijentId){
		System.out.println("Zakazujem pregled");
		Pregled p = PregledService.findById(pregledId);
		System.out.println(p.getTrajanje());
		Pacijent pacijent = PacijentService.findById(pacijentId);
		System.out.println(pacijent.getIme());
		p.setPacijent(pacijent);
		//pacijent.addPregled(p);
		
		try {
			p = PregledService.save(p);
		} catch (Exception e) {
			return new ResponseEntity<>(new PregledDTO(p), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(new PregledDTO(p), HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<PregledDTO> savePregled(@RequestBody PregledDTO PregledDTO) {

		Pregled Pregled = new Pregled();
		Pregled.setDatumVreme(PregledDTO.getDatumVreme());
		Pregled.setTrajanje(PregledDTO.getTrajanje());
		Pregled.setStatus(Status.odobreno);	
		TipPregleda tp= TipPregledaService.findByNaziv(PregledDTO.getTipPregleda().getNaziv()); 
		Pregled.setTipPregleda(tp);
		Sala s = SalaService.findByNaziv(PregledDTO.getSala().getNaziv());
		Pregled.setSala(s);
		Lekar l = LekarService.findByEmail(PregledDTO.getLekar().getEmail());
		Pregled.setLekar(l);
		
		
		try {
			Pregled = PregledService.save(Pregled);
		} catch (Exception e) {
			return new ResponseEntity<>(new PregledDTO(Pregled), HttpStatus.BAD_REQUEST);
		}


		return new ResponseEntity<>(new PregledDTO(Pregled), HttpStatus.CREATED);
	}
	
}
