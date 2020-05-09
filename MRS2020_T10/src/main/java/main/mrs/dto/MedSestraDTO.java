package main.mrs.dto;

import java.util.Set;

import main.mrs.model.MedSestra;




public class MedSestraDTO extends KorisnikDTO {
   public RadniKalendarDTO radKalendar;
   public KlinikaDTO klinika;
   
   
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



}