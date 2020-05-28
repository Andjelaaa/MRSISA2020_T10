package main.mrs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.mrs.dto.LekarDTO;
import main.mrs.dto.StavkaCenovnikaDTO;
import main.mrs.dto.TipPregledaDTO;
import main.mrs.model.AdminKlinike;
import main.mrs.model.Lekar;
import main.mrs.model.TipPregleda;
import main.mrs.service.AdminKlinikeService;
import main.mrs.service.TipPregledaService;

@RestController
@RequestMapping(value="api/tippregleda")
public class TipPregledaController {
	
	@Autowired
	private TipPregledaService TipPregledaService;
	@Autowired
	private AdminKlinikeService adminKlinikeService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<TipPregledaDTO>> getAllTipPregledas() {

		List<TipPregleda> TipPregledas = TipPregledaService.findAll();

		// convert TipPregledas to DTOs
		List<TipPregledaDTO> TipPregledasDTO = new ArrayList<>();
		for (TipPregleda s : TipPregledas) {
			TipPregledaDTO tp = new TipPregledaDTO(s);
			tp.setStavka(new StavkaCenovnikaDTO(s.getStavka()));
			TipPregledasDTO.add(tp);
		}

		return new ResponseEntity<>(TipPregledasDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/{searchParam}")
	public ResponseEntity<List<TipPregledaDTO>> getSearchTipPregleda(@PathVariable String searchParam) {
		System.out.println(searchParam);
		List<TipPregleda> TipPregledas = TipPregledaService.findSearchNaziv(searchParam.toUpperCase());

		// convert TipPregledas to DTOs
		List<TipPregledaDTO> TipPregledasDTO = new ArrayList<>();
		for (TipPregleda s : TipPregledas) {
			TipPregledasDTO.add(new TipPregledaDTO(s));
		}

		return new ResponseEntity<>(TipPregledasDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<TipPregledaDTO> saveTipPregleda(@RequestBody TipPregledaDTO TipPregledaDTO) {

		TipPregleda tipPregleda = new TipPregleda();
		tipPregleda.setNaziv(TipPregledaDTO.getNaziv());
		
		tipPregleda.setOpis(TipPregledaDTO.getOpis());
		tipPregleda.setBrojAktvnih(TipPregledaDTO.getBrojAktvnih());
		
		try {
			tipPregleda = TipPregledaService.save(tipPregleda);
		} catch (Exception e) {
			return new ResponseEntity<>(new TipPregledaDTO(tipPregleda), HttpStatus.BAD_REQUEST);
		}


		return new ResponseEntity<>(TipPregledaDTO, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{tipPregledaNaziv}/lekari/{idAdmina}")
	public ResponseEntity<List<LekarDTO>> getTipPregledaLekari(@PathVariable String tipPregledaNaziv, @PathVariable Integer idAdmina) {
		System.out.println("tipppp"+tipPregledaNaziv);
		TipPregleda tp = TipPregledaService.findByNaziv(tipPregledaNaziv);
		Set<Lekar> Lekari = tp.getLekar();
		
		AdminKlinike ak = adminKlinikeService.findOne(idAdmina);
		List<LekarDTO> LekariDTO = new ArrayList<>();
		for (Lekar l : Lekari) {
			System.out.println(l.getKlinika().getId()+"id llll");
			System.out.println(ak.getKlinika().getId()+"id aaaaa");
			if(l.getKlinika().getId() == ak.getKlinika().getId()) {
				System.out.println("tu sam");
				LekarDTO LekarDTO = new LekarDTO();
				LekarDTO.setIme(l.getIme());
				LekarDTO.setPrezime(l.getPrezime());
				LekarDTO.setEmail(l.getEmail());
				LekarDTO.setLozinka(l.getLozinka());
				LekarDTO.setGrad(l.getGrad());
				LekarDTO.setAdresa(l.getAdresa());
				LekarDTO.setDrzava(l.getDrzava());
				LekarDTO.setRvPocetak(l.getRvPocetak());
				LekarDTO.setRvKraj(l.getRvKraj());	
	
				LekariDTO.add(LekarDTO);
			}
		}
		return new ResponseEntity<>(LekariDTO, HttpStatus.OK);
	}
	
	@Transactional // obavezno ova anotacija, inace puca
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteTipPregleda(@PathVariable Integer id) {
		TipPregleda TipPregleda = TipPregledaService.findOne(id);

		if (TipPregleda != null) {
			try {
				TipPregledaService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				// postoji pregled tog tipa
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(consumes = "application/json", value = "/{id}")
	public ResponseEntity<TipPregledaDTO> updateSTipPregleda(@RequestBody TipPregledaDTO TipPregledaDTO, @PathVariable Integer id) {

		// a TipPregleda must exist
		TipPregleda TipPregleda = TipPregledaService.findOne(id);
		

		if (TipPregleda == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		TipPregleda.setNaziv(TipPregledaDTO.getNaziv());
		TipPregleda.setOpis(TipPregledaDTO.getOpis());

		TipPregleda = TipPregledaService.save(TipPregleda);
		return new ResponseEntity<>(new TipPregledaDTO(TipPregleda), HttpStatus.OK);
	}

}
