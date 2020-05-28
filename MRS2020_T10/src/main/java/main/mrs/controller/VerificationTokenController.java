package main.mrs.controller;


import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import main.mrs.dto.ZahtevRegDTO;
import main.mrs.model.Pacijent;
import main.mrs.model.VerificationToken;
import main.mrs.model.ZKarton;
import main.mrs.model.ZahtevReg;
import main.mrs.service.AutoritetService;
import main.mrs.service.PacijentService;
import main.mrs.service.VerificationTokenService;
import main.mrs.service.ZahtevRegService;
import main.mrs.verification_handler.OnAccessLinkEvent;

@Controller
@RequestMapping(value="api/verification")
public class VerificationTokenController {

//	@GetMapping("/odbijen-pristup")
//	public String nijeAktiviranLinkGreska() {
//		return "odbijen pristup";
//	}
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private ZahtevRegService zahtevService;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Autowired
	private AutoritetService autoritetService;
	@Autowired
	private VerificationTokenService verificationService;
	
	@Transactional
	@PostMapping(value= "/accepted")
	@PreAuthorize("hasAnyRole('ADMIN_KLINICKOG_CENTRA')")
	public ResponseEntity acceptedRegAsync(@RequestBody ZahtevRegDTO zahtev, HttpServletRequest request){
		
		Pacijent registrovaniPacijent = new Pacijent();
		registrovaniPacijent.setAdresa(zahtev.getAdresa());
		registrovaniPacijent.setIme(zahtev.getIme());
		registrovaniPacijent.setPrezime(zahtev.getPrezime());
		registrovaniPacijent.setEmail(zahtev.getEmail());
		registrovaniPacijent.setKontakt(zahtev.getKontakt());
		registrovaniPacijent.setGrad(zahtev.getGrad());
		registrovaniPacijent.setLozinka(pacijentService.encodePassword(zahtev.getLozinka()));
		registrovaniPacijent.setDrzava(zahtev.getDrzava());
		registrovaniPacijent.setLbo(zahtev.getLbo());
		registrovaniPacijent.setPromenioLozinku(false);
	
		registrovaniPacijent.setAutoriteti(autoritetService.findByName("ROLE_PACIJENT"));
		registrovaniPacijent.setzKarton(new ZKarton());
		
		//ZKARTON ZKARYON i id pacijenta 
		
		ZahtevReg user = new ZahtevReg();
		user.setAdresa(zahtev.getAdresa());
		user.setIme(zahtev.getIme());
		user.setPrezime(zahtev.getPrezime());
		user.setEmail(zahtev.getEmail());
		user.setKontakt(zahtev.getKontakt());
		user.setGrad(zahtev.getGrad());
		user.setLozinka(zahtev.getLozinka());
		user.setDrzava(zahtev.getDrzava());
		user.setLbo(zahtev.getLbo());
		
		
		try {
		    ZahtevReg brisi = zahtevService.findByEmail(user.getEmail());
			zahtevService.remove(brisi.getId());// zasto ne obrise
			registrovaniPacijent = pacijentService.save(registrovaniPacijent);
			eventPublisher.publishEvent(new OnAccessLinkEvent(registrovaniPacijent,
					request.getLocale(), request.getContextPath()));
			System.out.println("Gde ode");
			return new ResponseEntity<>(HttpStatus.OK);
		}catch( Exception e ){
			System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	
	}
	
	@GetMapping("/potvrdiRegistraciju/{token}")
	public String confirmRegistration(@PathVariable String token,HttpServletRequest request) {

		//Locale locale = request.getLocale();
		//System.out.println("odjedsadsassdsdsdasddsd");
		
		VerificationToken verificationToken = verificationService.findByToken(token);
//		if(verificationToken == null) {
//			//String message = messages.getMessage("auth.message.invalidToken", null, locale);
//			return "redirect:access-denied";
//		}
		Pacijent pacijent = verificationToken.getPacijent();
		Calendar calendar = Calendar.getInstance();
//		if((verificationToken.getDatumUnistavanja().getTime()-calendar.getTime().getTime())<=0) {
//			//String message = messages.getMessage("auth.message.expired", null, locale);
//			//model.addAttribute("message", message);
//			return "redirect:access-denied";
//		}
		
		pacijent.setAktivan(true);
		System.out.println("odje");
		pacijentService.save(pacijent);
		System.out.println("odje1232132312312");
		return null;
	}
	
	@GetMapping(value = "/enkodujLozinku/{lozinka}")
	public ResponseEntity<String> enkodujLozinku(@PathVariable String lozinka) {
		
		String enkodovana = pacijentService.encodePassword(lozinka);
		return new ResponseEntity<>(enkodovana, HttpStatus.OK);
	}

	
}
