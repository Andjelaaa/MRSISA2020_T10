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

import main.mrs.dto.ZahtevRegDTO;
import main.mrs.model.ZahtevReg;
import main.mrs.service.ZahtevRegService;

@RestController
@RequestMapping(value="api/zahtevreg")
public class ZahtevRegController {

	@Autowired
	private ZahtevRegService ZahtevRegService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<ZahtevRegDTO>> getAllZahtevRegs() {

		List<ZahtevReg> ZahtevRegs = ZahtevRegService.findAll();

		// convert ZahtevRegs to DTOs
		List<ZahtevRegDTO> ZahtevRegsDTO = new ArrayList<>();
		for (ZahtevReg s : ZahtevRegs) {
			ZahtevRegsDTO.add(new ZahtevRegDTO(s));
		}

		return new ResponseEntity<>(ZahtevRegsDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<ZahtevRegDTO> saveZahtevReg(@RequestBody ZahtevRegDTO ZahtevRegDTO) {

		ZahtevReg zahtevReg = new ZahtevReg();
		zahtevReg.setAdresa(ZahtevRegDTO.getAdresa());
		zahtevReg.setDrzava(ZahtevRegDTO.getDrzava());
		zahtevReg.setEmail(ZahtevRegDTO.getEmail());
		zahtevReg.setGrad(ZahtevRegDTO.getGrad());
		zahtevReg.setIme(ZahtevRegDTO.getIme());
		zahtevReg.setKontakt(ZahtevRegDTO.getKontakt());
		zahtevReg.setLozinka(ZahtevRegDTO.getLozinka());
		zahtevReg.setPrezime(ZahtevRegDTO.getPrezime());
		zahtevReg.setLbo(ZahtevRegDTO.getLbo());
		
		try {
			zahtevReg = ZahtevRegService.save(zahtevReg);
		} catch (Exception e) {
			return new ResponseEntity<>(new ZahtevRegDTO(zahtevReg), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new ZahtevRegDTO(zahtevReg), HttpStatus.CREATED);
	}
}
