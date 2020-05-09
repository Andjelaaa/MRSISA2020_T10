package main.mrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.mrs.dto.AdminKlinikeDTO;
import main.mrs.dto.KlinikaDTO;
import main.mrs.model.AdminKlinike;
import main.mrs.model.Klinika;
import main.mrs.service.AdminKlinikeService;

@RestController
@RequestMapping(value="api/admini")
public class AdminKlinikeController {

	@Autowired
	private AdminKlinikeService AdminKlinikeService;
	

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
	
	@GetMapping(value="/klinika/{emailAdmina}")
	public ResponseEntity<KlinikaDTO> dobaviKlinikuAdmina(@PathVariable String emailAdmina){
		System.out.println("USAO SAMMM");
		AdminKlinike ak = AdminKlinikeService.findByEmail(emailAdmina);
		System.out.println("ADMIN JE "+ak.getEmail());
		Klinika Klinika = ak.getKlinika();
		System.out.println(Klinika.getNaziv());
		return new ResponseEntity<>(new KlinikaDTO(Klinika), HttpStatus.OK);
	}

}
