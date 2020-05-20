package main.mrs.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.mrs.dto.PregledDTO;
import main.mrs.model.Lekar;
import main.mrs.model.OcenaKlinika;
import main.mrs.model.OcenaLekar;
import main.mrs.model.Ocene;
import main.mrs.model.Pacijent;
import main.mrs.model.PomocnaKlasa4;
import main.mrs.model.Pregled;
import main.mrs.model.Sala;
import main.mrs.model.Status;
import main.mrs.model.TipPregleda;
import main.mrs.model.ZKarton;
import main.mrs.model.ZahtevPregled;
import main.mrs.service.EmailService;
import main.mrs.service.KlinikaService;
import main.mrs.service.LekarService;
import main.mrs.service.OcenaKlinikaService;
import main.mrs.service.OcenaLekarService;
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
	@Autowired
	private KlinikaService KlinikaService;
	@Autowired
	private OcenaLekarService OcenaLekarService;
	@Autowired
	private OcenaKlinikaService OcenaKlinikaService;
	
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
	
	@PostMapping(value = "/ocene")
	public ResponseEntity<Ocene> dobaviOcene(@RequestBody PregledDTO data) {
		System.out.println("Dosao sam ovde da dobavim ocene");
		Ocene ocene = new Ocene(); // podesi ocene na 0, ako je 0 onda nije ni ocenjeno
		
		// dobavimo stare ocene ako postoje
		// kad namestimmo da se setuje i klinika za lekara odkomentarisatiii
		// int klinikaId = data.getLekar().getKlinika().getId();
		int klinikaId = 1;
		int pacijentId = data.getPacijent().getId();
		int lekarId = data.getLekar().getId();
		OcenaLekar ocenaLekara = OcenaLekarService.findOcenu(lekarId, pacijentId);
		OcenaKlinika ocenaKlinike = OcenaKlinikaService.findOcenu(klinikaId, pacijentId);
		
		if(ocenaLekara != null)
		{
			ocene.ocenaLekar = ocenaLekara.getOcena();
		}
		if(ocenaKlinike != null)
		{
			ocene.ocenaKlinika = ocenaKlinike.getOcena();
		}
		
		return new ResponseEntity<>(ocene, HttpStatus.OK);
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
	@PostMapping(value = "izmenikarton/{pacijentId}")
	public ResponseEntity<String> izmeniKarton(@RequestBody PomocnaKlasa4 klasa, @PathVariable Integer pacijentId){
		Pacijent pacijent = PacijentService.findById(pacijentId);
		ZKarton karton = pacijent.getzKarton();
		
	    karton.setDioptrija(klasa.dioptrija);
	    karton.setTezina(klasa.tezina);
	    karton.setVisina(klasa.visina);
	    karton.setKrvnaGrupa(klasa.krvnaGrupa);
		
		try {
			pacijent = PacijentService.save(pacijent);
		}
		catch(Exception e) {
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>( HttpStatus.OK);
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
	
	@PostMapping(consumes = "application/json;charset=UTF-8", value="/lekarzahtev")
	public ResponseEntity<PregledDTO> saveZahtevLekar(@RequestBody PregledDTO PregledDTO) {
		Pregled Pregled = new Pregled();
		Pregled.setDatumVreme(PregledDTO.getDatumVreme());
		Pregled.setTrajanje(PregledDTO.getTrajanje());
		Pregled.setStatus(Status.zahtev_lekar);	
		Pregled.setPopust(0.0);
		TipPregleda tp= TipPregledaService.findByNaziv(PregledDTO.getLekar().getTipPregleda().getNaziv()); 
		Pregled.setTipPregleda(tp);
		Pregled.setSala(null);
		Lekar l = LekarService.findByEmail(PregledDTO.getLekar().getEmail());
		Pregled.setLekar(l);
		Pacijent p = PacijentService.findByEmail(PregledDTO.getPacijent().getEmail());
		Pregled.setPacijent(p);
		
		// OVO NE BRISATI
		System.out.println(l.getKlinika().getAdminKlinike().isEmpty());
		
		try {
			Pregled = PregledService.save(Pregled);
			EmailService.mailAdminuZakazanPregled(Pregled);
		} catch (Exception e) {
			return new ResponseEntity<>(new PregledDTO(Pregled), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new PregledDTO(Pregled), HttpStatus.CREATED);
	}
	

	@PostMapping(consumes = "application/json;charset=UTF-8", value="/pacijentzahtev")
	public ResponseEntity<PregledDTO> saveZahtevPacijent(@RequestBody ZahtevPregled zahtev) {
		System.out.println("E cao saljem zahtev za dan " + zahtev.datum);
		Pregled Pregled = new Pregled();
		//Pregled.setDatumVreme(PregledDTO.getDatumVreme());
		String datumVreme = zahtev.datum + " " + zahtev.vreme;
		//2020-05-21 hh:mm
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm HH:mm");
		
		try {
			System.out.println("Sad cu da parsiram datum");
			Pregled.setDatumVreme((Date)(sdf.parse(datumVreme)));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			System.out.println("E nije uspelo parsiranje");
			e1.printStackTrace();
		}
		Pacijent p = PacijentService.findById(Integer.parseInt(zahtev.pacijentId));
		Pregled.setPacijent(p);
		//Klinika k = KlinikaService.findOne(Integer.parseInt(zahtev.klinikaId));
		Pregled.setTrajanje(30);
		Pregled.setStatus(Status.zahtev);	
		Pregled.setPopust(0.0);
		TipPregleda tp= TipPregledaService.findByNaziv(zahtev.tipPregledaNaziv); 
		Pregled.setTipPregleda(tp);
		Pregled.setSala(null);
		Lekar l = LekarService.findByEmail(zahtev.lekarEmail);
		Pregled.setLekar(l);
		
		
		try {
			System.out.println(" pokusacu da ga sacuvam");
			Pregled = PregledService.save(Pregled);
		} catch (Exception e) {
			System.out.println("E nije uspelo cuvanje");
			return new ResponseEntity<>(new PregledDTO(Pregled), HttpStatus.BAD_REQUEST);
		}
		System.out.println("E SVE JE OOKKKKKK");
		return new ResponseEntity<>(new PregledDTO(Pregled), HttpStatus.CREATED);
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/rezervisi/{pregledId}/{salaId}/{prviSlobodan}")
	public ResponseEntity<PregledDTO> rezervisiSaluZaPregled(@PathVariable Integer pregledId, @PathVariable Integer salaId, @PathVariable String prviSlobodan){
		sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS");
		
		Date datum = null;
		try {
			datum = sdf.parse(prviSlobodan);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Pregled p = PregledService.findById(pregledId);
		Sala s = SalaService.findOne(salaId);
		p.setSala(s);
		p.setStatus(Status.odobreno);
		datum.setHours(datum.getHours()+2);
		p.setDatumVreme(datum);
		try {
			PregledService.save(p);
			EmailService.posaljiPacijentuOdobrenPregled(p);
			EmailService.posaljiLekaruOdobrenPregled(p);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(new PregledDTO(p), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(new PregledDTO(p), HttpStatus.OK);
	}
	
}
