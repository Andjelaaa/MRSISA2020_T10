package main.mrs.dto;

import java.util.Set;




public class MedSestraDTO extends KorisnikDTO {
   public RadniKalendarDTO radKalendar;
   public KlinikaDTO klinika;
   
   
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