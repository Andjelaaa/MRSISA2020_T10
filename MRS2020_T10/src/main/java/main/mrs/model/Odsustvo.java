package main.mrs.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import main.mrs.dto.LekarDTO;
import main.mrs.dto.MedSestraDTO;
@Entity
@Table(name="ODSUSTVO")
public class Odsustvo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="status", unique=false, nullable=false)
	private Status status;
	@Column(name="tip", unique=false, nullable=false)
	private String tip;
	@Column(name="opis", unique=false, nullable=true)
	private String opis;
	
	@Column(name="pocetak", unique=false, nullable=false)
	private Date pocetak;
	
	@Column(name="kraj", unique=false, nullable=false)
	private Date kraj;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="medSestra_id", nullable=true)
	private MedSestra medSestra;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="lekar_id", nullable=true)
	private Lekar lekar;
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Date getPocetak() {
		return pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public Date getKraj() {
		return kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}

	public MedSestra getSestra() {
		return medSestra;
	}

	public void setSestra(MedSestraDTO sestra) {
		
		MedSestra ms = new MedSestra();
		ms.setAdresa(sestra.getAdresa());
		ms.setDrzava(sestra.getDrzava());
		ms.setEmail(sestra.getEmail());
		ms.setGrad(sestra.getGrad());
		ms.setIme(sestra.getIme());
		ms.setPrezime(sestra.getPrezime());
		ms.setKlinika(null);// treba da se namesti konvertor IZ DTO
		ms.setKontakt(sestra.getKontakt());
		ms.setLozinka(sestra.getLozinka());
		ms.setRadKalendar(null);// i ovooooooooooo
		ms.setRadvr_kraj(sestra.getRadvr_kraj());
		ms.setRadvr_pocetak(sestra.getRadvr_pocetak());
		
	}

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(LekarDTO lekar) {
		Lekar le = new Lekar();
		le.setAdresa(lekar.getAdresa());
		le.setDrzava(lekar.getDrzava());
		le.setEmail(lekar.getEmail());
		le.setGrad(lekar.getGrad());
		le.setIme(lekar.getIme());
		le.setPrezime(lekar.getPrezime());
		le.setKlinika(null);// treba da se namesti konvertor IZ DTO
		le.setKontakt(lekar.getKontakt());
		le.setLozinka(lekar.getLozinka());
		//le.setRadKalendar(null);// i ovooooooooooo
		le.setRvKraj(lekar.getRvKraj());
		le.setRvPocetak(lekar.getRvPocetak());

	}

}