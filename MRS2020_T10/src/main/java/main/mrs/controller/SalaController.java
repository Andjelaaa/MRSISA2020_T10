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

import main.mrs.dto.SalaDTO;
import main.mrs.model.Sala;
import main.mrs.service.SalaService;

@RestController
@RequestMapping(value="api/sala")
public class SalaController {

	@Autowired
	private SalaService SalaService;

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
}
