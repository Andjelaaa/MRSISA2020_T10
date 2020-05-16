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
import main.mrs.dto.OperacijaDTO;


import main.mrs.model.Operacija;
import main.mrs.service.OperacijaService;
import main.mrs.service.LekarService;

@RestController
@RequestMapping(value="api/operacije")
public class OperacijaController {

	@Autowired
	private OperacijaService OperacijaService;

	@Transactional // obavezno ova anotacija, inace puca
	@GetMapping(value="/lekarop/{id}")
	public ResponseEntity<List<OperacijaDTO>> dobaviLekaroveOperacije(@PathVariable Integer id){
		//lekar_id
		
		List<Integer> idOperacija = OperacijaService.findByLekarId(id);

		List<Operacija> operacije = OperacijaService.findAllById(idOperacija);
		
		
		List<OperacijaDTO> operacijeDTO = new ArrayList<>();
		for (Operacija s : operacije) {
			operacijeDTO.add(new OperacijaDTO(s));
		}
		
		return new ResponseEntity<>(operacijeDTO, HttpStatus.OK);
		
	
	}


}
