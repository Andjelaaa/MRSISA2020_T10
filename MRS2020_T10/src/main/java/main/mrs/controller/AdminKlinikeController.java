package main.mrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.mrs.dto.AdminKlinikeDTO;
import main.mrs.dto.KlinikaDTO;
import main.mrs.dto.LekarDTO;
import main.mrs.model.AdminKlinike;
import main.mrs.model.Klinika;
import main.mrs.service.AdminKlinikeService;
import main.mrs.service.LekarService;
import main.mrs.service.PacijentService;

@RestController
@RequestMapping(value="api/admini")
public class AdminKlinikeController {

	@Autowired
	private AdminKlinikeService AdminKlinikeService;
	@Autowired
	private PacijentService PacijentService;
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<AdminKlinikeDTO> saveKlinika(@RequestBody AdminKlinikeDTO AdminKlinikeDTO) {
		
		AdminKlinike admin = new AdminKlinike();
		admin.setIme(AdminKlinikeDTO.getIme());
		admin.setPrezime(AdminKlinikeDTO.getPrezime());
		admin.setEmail(AdminKlinikeDTO.getEmail());
		admin.setKontakt(AdminKlinikeDTO.getKontakt());
		admin.setAdresa(AdminKlinikeDTO.getAdresa());
		admin.setGrad(AdminKlinikeDTO.getGrad());
		admin.setLozinka(AdminKlinikeDTO.getLozinka());
		admin.setDrzava(AdminKlinikeDTO.getDrzava());
		
		try {
			admin = AdminKlinikeService.save(admin);
		} catch (Exception e) {
			return new ResponseEntity<>(new AdminKlinikeDTO(admin), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new AdminKlinikeDTO(admin), HttpStatus.CREATED);
	}
	
	private String email = "";
	@GetMapping(value="/klinika/{emailAdmina}")
	public ResponseEntity<KlinikaDTO> dobaviKlinikuAdmina(@PathVariable String emailAdmina){
		if(!emailAdmina.equals("a"))
			this.email = emailAdmina;
		
		AdminKlinike ak = AdminKlinikeService.findByEmail(this.email);
		Klinika Klinika = ak.getKlinika();
		System.out.println(Klinika.getNaziv());
		return new ResponseEntity<>(new KlinikaDTO(Klinika), HttpStatus.OK);
	}
	
	@PutMapping(consumes = "application/json", value = "/{id}")
	public ResponseEntity<AdminKlinikeDTO> updateLekar(@RequestBody AdminKlinikeDTO lDTO, @PathVariable Integer id) {

		AdminKlinike l = AdminKlinikeService.findOne(id);

		if (l == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		l.setIme(lDTO.getIme());
		l.setPrezime(lDTO.getPrezime());
		l.setEmail(lDTO.getEmail());
		l.setAdresa(lDTO.getAdresa());
		l.setGrad(lDTO.getGrad());
		l.setDrzava(lDTO.getDrzava());
		l.setKontakt(lDTO.getKontakt());
		try {
			l = AdminKlinikeService.save(l);
			return new ResponseEntity<>(new AdminKlinikeDTO(), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@PutMapping(value = "promenaLozinke/{id}/{novaLozinka}")
	public ResponseEntity<AdminKlinikeDTO> updateLekarLozinka(@PathVariable Integer id, @PathVariable String novaLozinka) {

		AdminKlinike l = AdminKlinikeService.findOne(id);

		if (l == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		l.setLozinka(PacijentService.encodePassword(novaLozinka));
		try {
			l = AdminKlinikeService.save(l);
			return new ResponseEntity<>(new AdminKlinikeDTO(), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}


}
