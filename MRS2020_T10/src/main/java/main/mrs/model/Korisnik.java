package main.mrs.model;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

//@Entity
//@Inheritance(strategy=TABLE_PER_CLASS)

public abstract class Korisnik {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	
	//@Column(name="email", unique=true, nullable=false)
	private String email;
	
	//@Column(name="lozinka", unique=false, nullable=false)
	private String lozinka;
	
	//@Column(name="ime", unique=false, nullable=false)
	private String ime;
	
	//@Column(name="prezime", unique=false, nullable=false)
	private String prezime;
	
	//@Column(name="adresa", unique=false, nullable=false)
	private String adresa;
	
	//@Column(name="grad", unique=false, nullable=false)
	private String grad;
	
	//@Column(name="drzava", unique=false, nullable=false)
	private String drzava;
	
	//@Column(name="kontakt", unique=false, nullable=false)
	private String kontakt;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

}