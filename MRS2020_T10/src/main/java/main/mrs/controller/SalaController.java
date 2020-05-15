package main.mrs.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.mrs.dto.SalaDTO;
import main.mrs.dto.ZauzeceDTO;
import main.mrs.model.Pregled;
import main.mrs.model.Sala;
import main.mrs.service.PregledService;
import main.mrs.service.SalaService;


@RestController
@RequestMapping(value="api/sala")
public class SalaController {

	@Autowired
	private SalaService SalaService;
	
	@Autowired
	private PregledService PregledService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<SalaDTO>> getAllSalas() {

		List<Sala> Salas = SalaService.findAll();

		// convert Salas to DTOs
		List<SalaDTO> SalasDTO = new ArrayList<>();
		for (Sala s : Salas) {
			SalasDTO.add(new SalaDTO(s));
		}

		return new ResponseEntity<>(SalasDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<SalaDTO> saveSala(@RequestBody SalaDTO SalaDTO) {

		Sala Sala = new Sala();
		Sala.setNaziv(SalaDTO.getNaziv());
		Sala.setBroj(SalaDTO.getBroj());
		// TODO: za kliniku staviti kliniku od ulogovanog administratora klinike
		//Sala.setKlinika();
		
		try {
			Sala = SalaService.save(Sala);
		} catch (Exception e) {
			return new ResponseEntity<>(new SalaDTO(Sala), HttpStatus.BAD_REQUEST);
		}


		return new ResponseEntity<>(new SalaDTO(Sala), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/search/{searchParam}")
	public ResponseEntity<List<SalaDTO>> getSearchSala(@PathVariable String searchParam) {
		System.out.println(searchParam);
		List<Sala> Salas = SalaService.findSearchNaziv(searchParam.toUpperCase());

		// convert Salas to DTOs
		List<SalaDTO> SalasDTO = new ArrayList<>();
		for (Sala s : Salas) {
			SalasDTO.add(new SalaDTO(s));
		}

		return new ResponseEntity<>(SalasDTO, HttpStatus.OK);
	}
	
	@Transactional // obavezno ova anotacija, inace puca
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteSala(@PathVariable Integer id) {
		Sala Sala = SalaService.findOne(id);

		if (Sala != null) {
			// Provera da li je sala zauzeta ili rezervisana (postoje pregledi vezani za tu salu
			if(!Sala.getPregled().isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			SalaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(consumes = "application/json", value = "/{id}")
	public ResponseEntity<SalaDTO> updateSala(@RequestBody SalaDTO SalaDTO, @PathVariable Integer id) {

		// a Sala must exist
		Sala Sala = SalaService.findOne(id);

		if (Sala == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// Provera da li je sala zauzeta ili rezervisana (postoje pregledi vezani za tu salu
		if(!Sala.getPregled().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Sala.setNaziv(SalaDTO.getNaziv());
		Sala.setBroj(SalaDTO.getBroj());

		Sala = SalaService.save(Sala);
		return new ResponseEntity<>(new SalaDTO(Sala), HttpStatus.OK);
	}
	
	@GetMapping(value = "/zauzece/{idPregleda}/{idSale}")
	public ResponseEntity<List<ZauzeceDTO>> getSlobodne(@PathVariable int idPregleda, @PathVariable int idSale) {

		Pregled pregled = PregledService.findById(idPregleda);
		System.out.println(pregled.getDatumVreme());
		Sala sala = SalaService.findOne(idSale);
		List<Pregled> Pregleds = PregledService.findAll();
		
		List<ZauzeceDTO> zauzecaDTO = new ArrayList<ZauzeceDTO>();
		final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
		for(Pregled p: Pregleds) {
			if(p.getSala() != null) {
				if(p.getSala().getId() == sala.getId()) {
					
				    long curTimeInMs = p.getDatumVreme().getTime();
				    Date afterAddingMins = new Date(curTimeInMs + (p.getTrajanje() * ONE_MINUTE_IN_MILLIS));
					ZauzeceDTO z = new ZauzeceDTO(p.getDatumVreme(), afterAddingMins);
					zauzecaDTO.add(z);
				}
			}
		}

		return new ResponseEntity<>(zauzecaDTO, HttpStatus.OK);
	}
	
}
