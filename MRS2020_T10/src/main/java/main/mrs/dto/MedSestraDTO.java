package main.mrs.dto;

import java.util.Set;

import javax.persistence.Column;

import main.mrs.model.MedSestra;




public class MedSestraDTO extends KorisnikDTO {
   public RadniKalendarDTO radKalendar;
   public KlinikaDTO klinika;
   private String radvr_pocetak;
   private String radvr_kraj;
   
   public MedSestraDTO(MedSestra s) {
	   this(s.getId(), s.getEmail(), s.getLozinka(), s.getIme(), s.getPrezime(), s.getAdresa(), s.getGrad(),
				s.getDrzava(), s.getKontakt());
	}
	
	
	public MedSestraDTO(Integer id, String email, String lozinka, String ime, String prezime, String adresa, String grad,
			String drzava, String kontakt) {
		this.id = id;
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
	
	}
	
	
	public MedSestraDTO() {
		// TODO Auto-generated constructor stub
	}


	public KlinikaDTO getKlinika() {
		return klinika;
	}

	
	public void setKlinika(KlinikaDTO klinika) {
		this.klinika = klinika;
	}
	
	
	public RadniKalendarDTO getRadKalendar() {
		return radKalendar;
	}
	
	
	public void setRadKalendar(RadniKalendarDTO radKalendar) {
		this.radKalendar = radKalendar;
	}


	public String getRadvr_pocetak() {
		return radvr_pocetak;
	}


	public void setRadvr_pocetak(String radvr_pocetak) {
		this.radvr_pocetak = radvr_pocetak;
	}


	public String getRadvr_kraj() {
		return radvr_kraj;
	}


	public void setRadvr_kraj(String radvr_kraj) {
		this.radvr_kraj = radvr_kraj;
	}
	
	
	
}