package main.mrs.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.mrs.dto.IzvestajDTO;
import main.mrs.dto.LekDTO;
import main.mrs.model.Dijagnoza;
import main.mrs.model.Izvestaj;
import main.mrs.model.Lek;
import main.mrs.model.Pregled;
import main.mrs.model.Recept;
import main.mrs.model.Status;
import main.mrs.service.DijagnozaService;
import main.mrs.service.IzvestajService;
import main.mrs.service.LekService;
import main.mrs.service.ReceptService;
import main.mrs.service.PregledService;

@RestController
@RequestMapping(value="api/izvestaj")
public class IzvestajController {
	@Autowired
	private IzvestajService IzvestajService;
	
	@Autowired
	private ReceptService ReceptService;
	@Autowired
	private LekService LekService;
	@Autowired
	private DijagnozaService DijagnozaService;
	
	@Autowired
	private PregledService PregledService;
	@GetMapping(value = "/all")
	public ResponseEntity<List<IzvestajDTO>> getAllIzvestaje() {

		List<Izvestaj> izvestaji = IzvestajService.findAll();

		List<IzvestajDTO> izvestajiDTO = new ArrayList<>();
		for (Izvestaj s : izvestaji) {
			izvestajiDTO.add(new IzvestajDTO(s));
		}

		return new ResponseEntity<>(izvestajiDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json", value= "/{id_pregleda}")
	public ResponseEntity<IzvestajDTO> sacuvajIzvestaj(@RequestBody IzvestajDTO IzvestajDTO, @PathVariable Integer id_pregleda) {
		Izvestaj izvestaj = new Izvestaj();
		try {
			Recept recept = new Recept();
			recept.setMedSestra(null);
			Set<Lek> lekovi = new HashSet<Lek>();
			for(LekDTO dto : IzvestajDTO.getRecept().getLek()) {
				Lek lek = LekService.findByNaziv(dto.getNaziv());
				lekovi.add(lek);	
			}
			recept.setLek(lekovi);
			recept = ReceptService.save(recept);
			
			Dijagnoza dijagnoza = DijagnozaService.findByNaziv(IzvestajDTO.getDijagnoza().getNaziv());
			
			
			
			izvestaj.setDijagnoza(dijagnoza);
			izvestaj.setOpis(IzvestajDTO.getOpis());
			izvestaj.setRecept(recept);
			
			izvestaj = IzvestajService.save(izvestaj);
			Pregled pregled = PregledService.findById(id_pregleda);
			
			
			pregled.setIzvestaj(izvestaj);
			pregled.setStatus(Status.zavrseno);
			
			pregled = PregledService.save(pregled);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new IzvestajDTO(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new IzvestajDTO(izvestaj), HttpStatus.CREATED);
	}

}
