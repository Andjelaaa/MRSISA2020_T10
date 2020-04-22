package main.mrs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

//@Entity
@MappedSuperclass
public abstract class Korisnik {
	
	
//	@GeneratedValue(generator = "Korisnik_SequenceStyleGenerator")
//	@GenericGenerator(name = "Korisnik_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
//			 @Parameter(name = "sequence_name", value = "Korisnik_SEQ"),
//			 //@Parameter(name = "optimizer", value = "hilo"),
//			 @Parameter(name = "initial_value", value = "1"),
//			 @Parameter(name = "increment_size", value = "1") }
//			 )
	@Id
	@Column(name="id", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name="email", unique=true, nullable=false)
	private String email;
	
	@Column(name="lozinka", unique=false, nullable=false)
	private String lozinka;
	
	@Column(name="ime", unique=false, nullable=false)
	private String ime;
	
	@Column(name="prezime", unique=false, nullable=false)
	private String prezime;
	
	@Column(name="adresa", unique=false, nullable=false)
	private String adresa;
	
	@Column(name="grad", unique=false, nullable=false)
	private String grad;
	
	@Column(name="drzava", unique=false, nullable=false)
	private String drzava;
	
	@Column(name="kontakt", unique=false, nullable=false)
	private String kontakt;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
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