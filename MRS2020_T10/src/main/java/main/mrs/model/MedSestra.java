package main.mrs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="MedicinskaSestra")
public class MedSestra extends Korisnik {
	
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Klinika klinika;
   
   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   // @JoinColumn(name="medSestra", nullable=false)
    public RadniKalendar radniKalendar;
   
  
	public RadniKalendar getRadKalendar() {
		return radniKalendar;
	}
	
	
	public void setRadKalendar(RadniKalendar radKalendar) {
		this.radniKalendar = radKalendar;
	}
	


	public Klinika getKlinika() {
		return klinika;
	}
	
	
	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}



}