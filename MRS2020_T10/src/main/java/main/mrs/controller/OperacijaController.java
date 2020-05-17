package main.mrs.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.mrs.dto.LekarDTO;
import main.mrs.dto.OperacijaDTO;
import main.mrs.dto.PregledDTO;
import main.mrs.model.Lekar;
import main.mrs.model.Operacija;
import main.mrs.model.Pacijent;
import main.mrs.model.Pregled;
import main.mrs.model.Sala;
import main.mrs.model.Status;
import main.mrs.service.EmailService;
import main.mrs.service.LekarService;
import main.mrs.service.OperacijaService;
import main.mrs.service.PacijentService;
import main.mrs.service.SalaService;

@RestController
@RequestMapping(value="api/operacije")
public class OperacijaController {

	private SimpleDateFormat sdf;
	@Autowired
	private OperacijaService OperacijaService;
	@Autowired
	private LekarService LekarService;
	@Autowired
	private PacijentService PacijentService;
	
	@Autowired
	private EmailService EmailService;

	@Autowired 
	private SalaService SalaService;
	
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
	
	@PostMapping(consumes = "application/json;charset=UTF-8", value="/lekarzahtev")
	public ResponseEntity<OperacijaDTO> saveZahtevLekar(@RequestBody OperacijaDTO OperacijaDTO) {
		Operacija Operacija = new Operacija();
		Operacija.setDatumVreme(OperacijaDTO.getDatumVreme());
		Operacija.setTrajanje(OperacijaDTO.getTrajanje());
		Operacija.setStatus(Status.zahtev_lekar);	
		Operacija.setSala(null);
		
		Lekar l = LekarService.findByEmail((new ArrayList<LekarDTO>(OperacijaDTO.getLekar())).get(0).getEmail());
		Set<Lekar> lekari = new HashSet<Lekar>();
		lekari.add(l);
		Operacija.setLekar(lekari);
		
		Pacijent p = PacijentService.findByEmail(OperacijaDTO.getPacijent().getEmail());
		Operacija.setPacijent(p);
		
		// OVO NE BRISATI
		System.out.println(l.getKlinika().getAdminKlinike().isEmpty());
		
		try {
			Operacija = OperacijaService.save(Operacija);
			EmailService.mailAdminuZakazanaOperacija(Operacija, l);
		} catch (Exception e) {
			return new ResponseEntity<>(new OperacijaDTO(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new OperacijaDTO(), HttpStatus.CREATED);
	}
	@GetMapping(value = "/zahtevi")
	public ResponseEntity<List<OperacijaDTO>> getZahtevi() {

		List<Operacija> operacije = OperacijaService.findAllZahtevi();

		List<OperacijaDTO> OperacijeDTO = new ArrayList<>();
		for (Operacija s : operacije) {
			OperacijaDTO operacija = new OperacijaDTO(s);
			OperacijeDTO.add(operacija);
			
		}

		return new ResponseEntity<>(OperacijeDTO, HttpStatus.OK);
	}
	
	
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/rezervisi/{operacijaId}/{salaId}/{prviSlobodan}")
	public ResponseEntity<OperacijaDTO> rezervisiSaluZaOperaciju(@PathVariable Integer operacijaId, @PathVariable Integer salaId, @PathVariable String prviSlobodan){
		sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS");
		
		Date datum = null;
		try {
			datum = sdf.parse(prviSlobodan);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Operacija operacija = OperacijaService.findOne(operacijaId);
		Sala s = SalaService.findOne(salaId);
		
		operacija.setSala(s);
		operacija.setStatus(Status.odobreno);
		datum.setHours(datum.getHours()+2);
		operacija.setDatumVreme(datum);
		try {
			operacija = OperacijaService.save(operacija);
			EmailService.posaljiPacijentuOdobrenaOperacija(operacija);
			EmailService.posaljiLekaruOdobrenaOperacija(operacija);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(new OperacijaDTO(operacija), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(new OperacijaDTO(operacija), HttpStatus.OK);
	}

}
