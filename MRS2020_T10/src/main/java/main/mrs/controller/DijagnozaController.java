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

import main.mrs.dto.DijagnozaDTO;
import main.mrs.dto.LekDTO;
import main.mrs.model.Dijagnoza;
import main.mrs.model.Lek;
import main.mrs.model.PomocnaKlasa2;
import main.mrs.model.PomocnaKlasa3;
import main.mrs.service.DijagnozaService;

@RestController
@RequestMapping(value="api/dijagnoze")
public class DijagnozaController {

	@Autowired
	private DijagnozaService DijagnozaService;
	

	@GetMapping(value = "/all")
	public ResponseEntity<List<DijagnozaDTO>> getAllDijagnoze() {

		List<Dijagnoza> dijagnoze = DijagnozaService.findAll();

		List<DijagnozaDTO> DijagnozeeDTO = new ArrayList<>();
		for (Dijagnoza s : dijagnoze) {
			DijagnozeeDTO.add(new DijagnozaDTO(s));
		}

		return new ResponseEntity<>(DijagnozeeDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<DijagnozaDTO> saveLekove(@RequestBody DijagnozaDTO DijagnozaDTO) {

		Dijagnoza dijagnoza = new Dijagnoza();
		dijagnoza.setNaziv(DijagnozaDTO.getNaziv());
		dijagnoza.setSifra(DijagnozaDTO.getSifra());
		
		try {
			List<Dijagnoza> dijagnoze = DijagnozaService.findAll();

			for (Dijagnoza s : dijagnoze) {
				if(s.getNaziv().equalsIgnoreCase(dijagnoza.getNaziv())) {
					throw new Exception();
					
				}
				if(s.getSifra().equalsIgnoreCase(dijagnoza.getSifra())) {
					throw new Exception();
				}
								
			}
			dijagnoza = DijagnozaService.save(dijagnoza);
		} catch (Exception e) {
			return new ResponseEntity<>(new DijagnozaDTO(dijagnoza), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new DijagnozaDTO(dijagnoza), HttpStatus.CREATED);
	}
	@PostMapping(value="/izmena",consumes = "application/json")
	public ResponseEntity<DijagnozaDTO> changeDijagnoza(@RequestBody PomocnaKlasa3 data) {
        DijagnozaDTO izmenjen = data.dijagnoza;
		try {
		     Dijagnoza nadjiDijagnozu = DijagnozaService.findByNaziv(data.naziv);
		     DijagnozaService.delete(nadjiDijagnozu);
		     nadjiDijagnozu.setNaziv(izmenjen.getNaziv());
		     nadjiDijagnozu.setSifra(izmenjen.getSifra());
		     List<Dijagnoza> dijagnoze = DijagnozaService.findAll();

				for (Dijagnoza s : dijagnoze) {
					if(s.getNaziv().equalsIgnoreCase(nadjiDijagnozu.getNaziv())) {
						throw new Exception();
						
					}
					if(s.getSifra().equalsIgnoreCase(nadjiDijagnozu.getSifra())) {
						throw new Exception();
					}
									
				}
		   
		     nadjiDijagnozu = DijagnozaService.save(nadjiDijagnozu);
		} catch (Exception e) {
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>( HttpStatus.CREATED);
	}
}
