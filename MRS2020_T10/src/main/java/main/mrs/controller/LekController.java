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

import main.mrs.dto.LekDTO;
import main.mrs.model.Lek;
import main.mrs.model.PomocnaKlasa2;
import main.mrs.service.LekService;

@RestController
@RequestMapping(value="api/lekovi")
public class LekController {

	@Autowired
	private LekService LekService;
	

	@GetMapping(value = "/all")
	public ResponseEntity<List<LekDTO>> getAllLekove() {

		List<Lek> lekovi = LekService.findAll();

		List<LekDTO> LeksDTO = new ArrayList<>();
		for (Lek s : lekovi) {
			LeksDTO.add(new LekDTO(s));
		}

		return new ResponseEntity<>(LeksDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<LekDTO> saveLekove(@RequestBody LekDTO LekDTO) {

		Lek lek = new Lek();
		lek.setNaziv(LekDTO.getNaziv());
		lek.setSifra(LekDTO.getSifra());
		
		try {
			
			List<Lek> lekovi = LekService.findAll();
			
			for (Lek s : lekovi) {
				if(s.getNaziv().equalsIgnoreCase(lek.getNaziv())) {
					throw new Exception();
					
				}
				if(s.getSifra().equalsIgnoreCase(lek.getSifra())) {
					throw new Exception();
				}
			}
			lek = LekService.save(lek);
		} catch (Exception e) {
			return new ResponseEntity<>(new LekDTO(lek), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new LekDTO(lek), HttpStatus.CREATED);
	}
	@PostMapping(value="/izmena",consumes = "application/json")
	public ResponseEntity<LekDTO> changeLek(@RequestBody PomocnaKlasa2 data) {
        LekDTO izmenjen = data.lek;
		try {
		     Lek nadjiLek = LekService.findByNaziv(data.naziv);
		     LekService.delete(nadjiLek);
		     System.out.println();
		     nadjiLek.setNaziv(izmenjen.getNaziv());
		     nadjiLek.setSifra(izmenjen.getSifra());
			 nadjiLek = LekService.save(nadjiLek);
		} catch (Exception e) {
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>( HttpStatus.CREATED);
	}
}
