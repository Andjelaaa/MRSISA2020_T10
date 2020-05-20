package main.mrs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.mrs.dto.LekarDTO;
import main.mrs.dto.SearchLekar;
import main.mrs.model.Lekar;
import main.mrs.model.PomocnaKlasa5;
import main.mrs.model.PomocnaKlasa6;
import main.mrs.model.TipPregleda;
import main.mrs.service.AutoritetService;
import main.mrs.service.LekarService;
import main.mrs.service.PacijentService;
import main.mrs.service.TipPregledaService;

@RestController
@RequestMapping(value="api/lekar")
public class LekarContoller {
	
	@Autowired
	private LekarService LekarService;
	
	@Autowired
	private PacijentService PacijentService;
	@Autowired
	private AutoritetService autoritetService;
	
	@Autowired
	TipPregledaService tps = new TipPregledaService();	

	@GetMapping(value = "/all")
	public ResponseEntity<List<LekarDTO>> getAllLekars() {

		List<Lekar> Lekars = LekarService.findAll();

		// convert Lekars to DTOs
		List<LekarDTO> LekarsDTO = new ArrayList<>();
		for (Lekar s : Lekars) {
			LekarsDTO.add(new LekarDTO(s));
		}

		return new ResponseEntity<>(LekarsDTO, HttpStatus.OK);
	}

	 @Transactional
	@PostMapping(consumes = "application/json")
	public ResponseEntity<LekarDTO> saveLekar(@RequestBody LekarDTO LekarDTO) {

		Lekar Lekar = new Lekar();
		Lekar.setIme(LekarDTO.getIme());
		Lekar.setPrezime(LekarDTO.getPrezime());
		Lekar.setEmail(LekarDTO.getEmail());
		Lekar.setLozinka(PacijentService.encodePassword(LekarDTO.getLozinka()));
		Lekar.setGrad(LekarDTO.getGrad());
		Lekar.setAdresa(LekarDTO.getAdresa());
		Lekar.setDrzava(LekarDTO.getDrzava());
		Lekar.setRvPocetak(LekarDTO.getRvPocetak());
		Lekar.setRvKraj(LekarDTO.getRvKraj());		
		Lekar.setKontakt(LekarDTO.getKontakt());
		Lekar.setProsecnaOcena(0.0);
		Lekar.setBrojOcena(0);
		TipPregleda tp= tps.findByNaziv(LekarDTO.getTipPregleda().getNaziv()); 
		Lekar.setTipPregleda(tp);
		Lekar.setAutoriteti(autoritetService.findByName("ROLE_LEKAR"));
		
		// TODO: za kliniku staviti kliniku od ulogovanog administratora klinike
		//Lekar.setKlinika();
		
		try {
			Lekar = LekarService.save(Lekar);
		} catch (Exception e) {
			return new ResponseEntity<>(new LekarDTO(Lekar), HttpStatus.BAD_REQUEST);
		}


		return new ResponseEntity<>(new LekarDTO(Lekar), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/search")
	public ResponseEntity<List<LekarDTO>> getSearchLekars(@RequestBody SearchLekar sl) {
		System.out.println(sl.getIme()+sl.getPrezime());
		List<Lekar> Lekars = LekarService.findByImeAndPrezime(sl.getIme().toUpperCase(), sl.getPrezime().toUpperCase());

		// convert Lekars to DTOs
		List<LekarDTO> LekarsDTO = new ArrayList<>();
		for (Lekar s : Lekars) {
			LekarsDTO.add(new LekarDTO(s));
		}

		return new ResponseEntity<>(LekarsDTO, HttpStatus.OK);
	}
	@PostMapping(value ="/slobodniLekari/search")
	public ResponseEntity<List<PomocnaKlasa5>> pretragaSlobodnihLkeara(@RequestBody PomocnaKlasa6 data)
	{
		
		List<PomocnaKlasa5> retVal = new ArrayList<PomocnaKlasa5>();
		for(PomocnaKlasa5 lekarTermini  : data.lekariTermini)
		{
			if (!data.ime.equals(""))
			{
				if(lekarTermini.lekar.getIme().toUpperCase().contains(data.ime.toUpperCase()))
				{
					//poklapanje po imenu
					if (!data.prezime.equals(""))
					{
						if(lekarTermini.lekar.getPrezime().toUpperCase().contains(data.prezime.toUpperCase()))
						{
							// poklapanje i po prezimenu ako je uneto
							if(data.ocena != 0)
							{
								if(lekarTermini.lekar.getProsecnaOcena()>= data.ocena && lekarTermini.lekar.getProsecnaOcena()<=data.ocena+1)
								{
									// poklapanje po svim kriterijumima
									// uzmi ovog
									retVal.add(lekarTermini);
									continue;
								}
								else {
									// nema poklapanja po oceni
									// preskoci
									continue;
								}
							} else {
								// poklapanje po imenu i prezimenu
								// uzmi ovog nije uneta ocena
								retVal.add(lekarTermini);
								continue;

							}
						}else
						{
							// ima poklapanje po imenu, ali nema po porezimenu
							// preskocoi
							continue;
						}
					}
					else if(data.ocena != 0)
					{
						// uneto ime i ocena
						// nije uneto prezime
						if(lekarTermini.lekar.getProsecnaOcena()>= data.ocena && lekarTermini.lekar.getProsecnaOcena()<=data.ocena+1)
						{
							// uzmi oog lekara
							retVal.add(lekarTermini);
							continue;
						}
						else {
							// nema poklapanja sa ocenom
							// preskoci ga
							continue;
						}
					}
					// nije uneto ni prezime ni ocena samo ime
					// uzmi ovog lekara
					retVal.add(lekarTermini);
					continue;
				}
			}
			else if (!data.prezime.equals(""))
			{
				// ime = null
				// prezime = "Uneto"
				if(lekarTermini.lekar.getPrezime().toUpperCase().contains(data.prezime.toUpperCase()))
				{
					// poklapanje po prezimenu
					if(data.ocena != 0)
					{
						// ime = null
						// prezime = "Uneto"
						// ocena = broj
						if(lekarTermini.lekar.getProsecnaOcena()>= data.ocena && lekarTermini.lekar.getProsecnaOcena()<=data.ocena+1)
						{
							// poklapanje 
							// uzmi ovog
							retVal.add(lekarTermini);
							continue;
						}
						else {
							// nema poklapanja po oceni, ne uzimaj ga
							// preskoci
							continue;
						}
					}
					else
					{
						// uneta pretraga samo po prezimenu
						// uzmi ovog
						retVal.add(lekarTermini);
						continue;
					}
					
				}else
				{
					// nema poklapanja po porezimenu idi dalje
					// preskoci
					continue;
				}
				
			}
			else if(data.ocena != 0)
			{
				// unet samo kriterijum ocene
				if(lekarTermini.lekar.getProsecnaOcena()>= data.ocena && lekarTermini.lekar.getProsecnaOcena()<=data.ocena+1)
				{
					// uneta samo pretraga po oceni
					// uzmi ovog
					retVal.add(lekarTermini);
					continue;
				}
			}
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@Transactional // obavezno ova anotacija, inace puca
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteLekar(@PathVariable Integer id) {
		Lekar Lekar = LekarService.findOne(id);

		if (Lekar != null) {
			// Provera da li je lekar ima zakazane preglede
//			if(!Lekar.getPregled().isEmpty()) {                                        ////DOVRSIITIIII
//				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//			}
			LekarService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
