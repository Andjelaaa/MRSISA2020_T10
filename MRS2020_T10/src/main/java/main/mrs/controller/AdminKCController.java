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

import main.mrs.dto.AdminKCDTO;
import main.mrs.model.AdminKC;
import main.mrs.service.AdminKCService;


@RestController
@RequestMapping(value = "api/adminkc")
public class AdminKCController {

	@Autowired
	private AdminKCService adminKCService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<AdminKCDTO>> getAllAdminKCs() {

		List<AdminKC> AdminKCs = adminKCService.findAll();

		// convert AdminKCs to DTOs
		List<AdminKCDTO> AdminKCsDTO = new ArrayList<>();
		for (AdminKC s : AdminKCs) {
			AdminKCsDTO.add(new AdminKCDTO(s));
		}

		return new ResponseEntity<>(AdminKCsDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<AdminKCDTO> saveAdminKC(@RequestBody AdminKCDTO AdminKCDTO) {

		AdminKC AdminKC = new AdminKC();
		AdminKC.setIme(AdminKCDTO.getIme());
		AdminKC.setPrezime(AdminKCDTO.getPrezime());
		AdminKC.setEmail(AdminKCDTO.getEmail());
		AdminKC.setLozinka(AdminKCDTO.getLozinka());
		AdminKC.setGrad(AdminKCDTO.getGrad());
		AdminKC.setAdresa(AdminKCDTO.getAdresa());
		AdminKC.setDrzava(AdminKCDTO.getDrzava());
		

		AdminKC = adminKCService.save(AdminKC);
		return new ResponseEntity<>(new AdminKCDTO(AdminKC), HttpStatus.CREATED);
	}
}
