package main.mrs.dto;

import java.util.HashSet;
import java.util.Set;

import main.mrs.model.AdminKC;


public class AdminKCDTO extends KorisnikDTO {
	//public Set<ZahtevRegDTO> zahtevReg;
	public KlinickiCentarDTO klinickiCentar;

	public AdminKCDTO(AdminKC s) {
		this(s.getId(), s.getEmail(), s.getLozinka(), s.getIme(), s.getPrezime(), s.getAdresa(), s.getGrad(),
				s.getDrzava());
	}

	public AdminKCDTO() {
	}

	public AdminKCDTO(Integer id, String email, String lozinka, String ime, String prezime, String adresa, String grad,
			String drzava) {
		this.id = id;
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;

	}

	public KlinickiCentarDTO getKlinickiCentar() {
		return klinickiCentar;
	}

	public void setKlinickiCentar(KlinickiCentarDTO klinickiCentar) {
		this.klinickiCentar = klinickiCentar;
	}

}