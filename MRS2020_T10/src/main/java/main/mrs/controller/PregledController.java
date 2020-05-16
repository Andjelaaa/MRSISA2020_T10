package main.mrs.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

import main.mrs.dto.OperacijaDTO;
import main.mrs.dto.PregledDTO;
import main.mrs.model.Lekar;
import main.mrs.model.Operacija;
import main.mrs.model.Pacijent;
import main.mrs.model.Pregled;
import main.mrs.model.Sala;
import main.mrs.model.Status;
import main.mrs.model.TipPregleda;
import main.mrs.service.EmailService;
import main.mrs.service.LekarService;
import main.mrs.service.PacijentService;
import main.mrs.service.PregledService;
import main.mrs.service.SalaService;
import main.mrs.service.TipPregledaService;

@RestController
@RequestMapping(value="api/pregled")
public class PregledController {
	private SimpleDateFormat sdf;
	@Autowired
	private PregledService PregledService;
	@Autowired
	private LekarService LekarService;
	@Autowired
	private SalaService SalaService;
	@Autowired
	private TipPregledaService TipPregledaService;
	@Autowired
	private EmailService EmailService;
	@Autowired
	private PacijentService PacijentService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<PregledDTO>> getAllPregleds() {

		List<Pregled> Pregleds = PregledService.findAll();

		// convert Pregleds to DTOs
		List<PregledDTO> PregledsDTO = new ArrayList<>();
		for (Pregled s : Pregleds) {
			PregledDTO pregled = new PregledDTO(s);
			pregled.getTipPregleda().getStavka().setCena(s.getTipPregleda().getStavka().getCena());
			pregled.setPopust(s.getPopust());
			PregledsDTO.add(pregled);
			
		}

		return new ResponseEntity<>(PregledsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/slobodniPregledi/{klinikaId}")
	public ResponseEntity<List<PregledDTO>> dobaviSlobodnePreglede(@PathVariable int klinikaId) {

		List<Pregled> Pregleds = PregledService.getUnscheduled(klinikaId);

		// convert Pregleds to DTOs
		List<PregledDTO> PregledsDTO = new ArrayList<>();
		for (Pregled s : Pregleds) {
			PregledDTO pregled = new PregledDTO(s);
			pregled.getTipPregleda().getStavka().setCena(s.getTipPregleda().getStavka().getCena());
			pregled.setPopust(s.getPopust());
			PregledsDTO.add(pregled);
		}
		
		return new ResponseEntity<>(PregledsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/zakazaniPregledi/{pacijentId}")
	public ResponseEntity<List<PregledDTO>> dobaviZakazanePreglede(@PathVariable int pacijentId) {

		List<Pregled> Pregleds = PregledService.getScheduled(pacijentId);

		// convert Pregleds to DTOs
		List<PregledDTO> PregledsDTO = new ArrayList<>();
		for (Pregled s : Pregleds) {
			PregledDTO pregled = new PregledDTO(s);
			pregled.getTipPregleda().getStavka().setCena(s.getTipPregleda().getStavka().getCena());
			pregled.setPopust(s.getPopust());
			PregledsDTO.add(pregled);
		}

		return new ResponseEntity<>(PregledsDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/{pacijent_id}/{lekar_id}")
	public ResponseEntity<PregledDTO>dobaviPregledeZaDan( @PathVariable Integer pacijent_id, @PathVariable Integer lekar_id) {

		sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Pregled> pregledi = PregledService.getPreglediByPL(pacijent_id, lekar_id);
       
		List<PregledDTO> preglediDTO = new ArrayList<>();
		for (Pregled s : pregledi) {
			
			if(sdf.format(s.getDatumVreme()).equals(sdf.format(new Date()))) {
				PregledDTO pregled = new PregledDTO(s);
				preglediDTO.add(pregled);
			}
			
		}
		if(preglediDTO.isEmpty())
			return new ResponseEntity<>(new PregledDTO(), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(preglediDTO.get(0), HttpStatus.OK);
	}
	@GetMapping(value = "/istorijaPregleda/{pacijentId}")
	public ResponseEntity<List<PregledDTO>> dobaviIstorijuPregleda(@PathVariable int pacijentId) {

		List<Pregled> Pregleds = PregledService.dobaviIstoriju(pacijentId);

		// convert Pregleds to DTOs
		List<PregledDTO> PregledsDTO = new ArrayList<>();
		for (Pregled s : Pregleds) {
			PregledDTO pregled = new PregledDTO(s);
			pregled.getTipPregleda().getStavka().setCena(s.getTipPregleda().getStavka().getCena());
			pregled.setPopust(s.getPopust());
			PregledsDTO.add(pregled);
		}

		return new ResponseEntity<>(PregledsDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/otkazi/{pregledId}/{pacijentId}")
	public ResponseEntity<PregledDTO> otkaziPregled(@PathVariable long pregledId, @PathVariable int pacijentId){
		Pregled p = PregledService.findById(pregledId);
		try {
			long minutes1 = p.getDatumVreme().getTime() / 60000 - 120; // omasi za 2 sata
			long minutes2 = new Date().getTime()/60000;
			if(minutes1 - minutes2 > 24*60)
			{
				Pacijent pacijent = PacijentService.findById(pacijentId);
				p.setPacijent(null);
				//pacijent.addPregled(p);
				p = PregledService.save(p);
				EmailService.posaljiObavestenjeOtkazanPregled(pacijent, p);
			}
			else {
				throw new Exception();
			}
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(new PregledDTO(p), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(new PregledDTO(p), HttpStatus.OK);
	}
	@GetMapping(value = "/lekarpre/{id}")
	public ResponseEntity<List<PregledDTO>> getAllPregledeLekara(@PathVariable Integer id){
		List<Pregled> pregledi = PregledService.findByLekarId(id);

		List<PregledDTO> preglediDTO = new ArrayList<>();
		for (Pregled s : pregledi) {
			preglediDTO.add(new PregledDTO(s));
		}
		return new ResponseEntity<>(preglediDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/{pregledId}/{pacijentId}")
	public ResponseEntity<PregledDTO> zakaziPregled(@PathVariable long pregledId, @PathVariable int pacijentId){
		Pregled p = PregledService.findById(pregledId);
		Pacijent pacijent = PacijentService.findById(pacijentId);
		p.setPacijent(pacijent);
		//pacijent.addPregled(p);
		
		try {
			p = PregledService.save(p);
			EmailService.posaljiObavestenjeZakazanPregled(pacijent, p);
			
		} catch (Exception e) {
			return new ResponseEntity<>(new PregledDTO(p), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(new PregledDTO(p), HttpStatus.OK);
	}
	@GetMapping(value = "/tip/{tipPregleda}")
	public ResponseEntity<List<PregledDTO>> zaTipPregleda(@PathVariable String tipPregleda) {
		TipPregleda tp = TipPregledaService.findByNaziv(tipPregleda);
		List<Pregled> result = PregledService.findByTipPregleda(tp.getId());
		List<PregledDTO> preglediDTO = new ArrayList<>();
		for (Pregled s : result) {
			PregledDTO pregled = new PregledDTO(s);
			pregled.getTipPregleda().getStavka().setCena(s.getTipPregleda().getStavka().getCena());
			pregled.setPopust(s.getPopust());
			preglediDTO.add(pregled);
		}
		return new ResponseEntity<>(preglediDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/datum/{datum}")
	public ResponseEntity<List<PregledDTO>> posleDatuma(@PathVariable String datum) {
		Date date1 = null;
		try {
			System.out.println(datum);
			date1=new SimpleDateFormat("yyyy-MM-dd").parse(datum);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		System.out.println(date1);
		List<Pregled> result = PregledService.findAfterDate(date1);
		List<PregledDTO> preglediDTO = new ArrayList<>();
		for (Pregled s : result) {
			PregledDTO pregled = new PregledDTO(s);
			pregled.getTipPregleda().getStavka().setCena(s.getTipPregleda().getStavka().getCena());
			pregled.setPopust(s.getPopust());
			preglediDTO.add(pregled);
		}
		return new ResponseEntity<>(preglediDTO, HttpStatus.OK);
	}
	
	
	
	@PostMapping(consumes = "application/json;charset=UTF-8")
	public ResponseEntity<PregledDTO> savePregled(@RequestBody PregledDTO PregledDTO) {
		Pregled Pregled = new Pregled();
		Pregled.setDatumVreme(PregledDTO.getDatumVreme());
		Pregled.setTrajanje(PregledDTO.getTrajanje());
		Pregled.setStatus(Status.odobreno);	
		Pregled.setPopust(PregledDTO.getPopust());
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
	
	@GetMapping(value = "/zahtevi")
	public ResponseEntity<List<PregledDTO>> getZahtevi() {

		List<Pregled> Pregleds = PregledService.findAllZahtevi();

		// convert Pregleds to DTOs
		List<PregledDTO> PregledsDTO = new ArrayList<>();
		for (Pregled s : Pregleds) {
			PregledDTO pregled = new PregledDTO(s);
			pregled.getTipPregleda().getStavka().setCena(s.getTipPregleda().getStavka().getCena());
			pregled.setPopust(s.getPopust());
			PregledsDTO.add(pregled);
			
		}

		return new ResponseEntity<>(PregledsDTO, HttpStatus.OK);
	}
	
}
