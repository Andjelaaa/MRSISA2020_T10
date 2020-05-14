package main.mrs.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.mrs.dto.MedSestraDTO;
import main.mrs.dto.OdsustvoDTO;
import main.mrs.dto.ReceptDTO;
import main.mrs.model.MedSestra;
import main.mrs.model.Recept;
import main.mrs.service.ReceptService;
import main.mrs.service.MedSestraService;

@RestController
@RequestMapping(value="api/recept")
public class ReceptController {
	@Autowired
	private ReceptService ReceptService;
	
	@Autowired
	private MedSestraService MedSestraService;
	
	
	@GetMapping(value = "/neovereni")
	public ResponseEntity<List<ReceptDTO>> getAllRecepte() {
		
		List<Recept> recepti = ReceptService.findAll();

		List<ReceptDTO> receptiDTO = new ArrayList<>();
		for (Recept r : recepti) {
			//if(r.getMedSestra() == null)
			receptiDTO.add(new ReceptDTO(r));
		}
		return new ResponseEntity<>(receptiDTO, HttpStatus.OK);
	}
	@PutMapping(value = "/izmeni/{email}", consumes = "application/json")
	public ResponseEntity<String> overiRecept(@RequestBody ReceptDTO ReceptDTO, @PathVariable String email) {
		
		System.out.println("///////////////////////////////////////////////////////////////////////");
		Recept recept = new Recept();
		
		recept.setId(ReceptDTO.getId());
		recept.setLek(Recept.changeDTO(ReceptDTO.getLek()));
		MedSestra m = MedSestraService.findByEmail(email);
		recept.setMedSestra(m);
		
		try {
			recept = ReceptService.save(recept);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>( HttpStatus.OK);
		
	}



}
