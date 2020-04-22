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

import main.mrs.dto.KlinikaDTO;
import main.mrs.model.Klinika;
import main.mrs.service.KlinikaService;;

@RestController
@RequestMapping(value="api/klinika")
public class KlinikaController {

	@Autowired
	private KlinikaService KlinikaService;
	@GetMapping(value = "/all")
	public ResponseEntity<List<KlinikaDTO>> getAllKlinike() {

		List<Klinika> Klinike = KlinikaService.findAll();

		// convert Klinike to DTOs
		List<KlinikaDTO> KlinikeDTO = new ArrayList<>();
		for (Klinika s : Klinike) {
			KlinikeDTO.add(new KlinikaDTO(s));
		}

		return new ResponseEntity<>(KlinikeDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<KlinikaDTO> saveKlinika(@RequestBody KlinikaDTO KlinikaDTO) {

		
		Klinika klinika = new Klinika();
		klinika.setNaziv(KlinikaDTO.getNaziv());
		klinika.setAdresa(KlinikaDTO.getAdresa());
		klinika.setOpis(KlinikaDTO.getOpis());
		klinika.setEmailKlinike(KlinikaDTO.getEmailKlinike());
		klinika.setKontaktKlinike(KlinikaDTO.getKontaktKlinike());

		try {
			klinika = KlinikaService.save(klinika);
		} catch (Exception e) {
			return new ResponseEntity<>(new KlinikaDTO(klinika), HttpStatus.BAD_REQUEST);
		}


		return new ResponseEntity<>(new KlinikaDTO(klinika), HttpStatus.CREATED);
	}

}
