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

import main.mrs.dto.TipPregledaDTO;
import main.mrs.model.TipPregleda;
import main.mrs.service.TipPregledaService;

@RestController
@RequestMapping(value="api/tippregleda")
public class TipPregledaController {
	
	@Autowired
	private TipPregledaService TipPregledaService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<TipPregledaDTO>> getAllTipPregledas() {

		List<TipPregleda> TipPregledas = TipPregledaService.findAll();

		// convert TipPregledas to DTOs
		List<TipPregledaDTO> TipPregledasDTO = new ArrayList<>();
		for (TipPregleda s : TipPregledas) {
			TipPregledasDTO.add(new TipPregledaDTO(s));
		}

		return new ResponseEntity<>(TipPregledasDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<TipPregledaDTO> saveTipPregleda(@RequestBody TipPregledaDTO TipPregledaDTO) {

		TipPregleda tipPregleda = new TipPregleda();
		tipPregleda.setNaziv(TipPregledaDTO.getNaziv());
		
		tipPregleda.setOpis(TipPregledaDTO.getOpis());
		tipPregleda.setBrojAktvnih(TipPregledaDTO.getBrojAktvnih());
		
		try {
			tipPregleda = TipPregledaService.save(tipPregleda);
		} catch (Exception e) {
			return new ResponseEntity<>(new TipPregledaDTO(tipPregleda), HttpStatus.BAD_REQUEST);
		}


		return new ResponseEntity<>(new TipPregledaDTO(tipPregleda), HttpStatus.CREATED);
	}

}
