package main.mrs.dto;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import main.mrs.model.Korisnik;
import main.mrs.model.Lekar;
import main.mrs.model.MedSestra;

public class OdsustvoDTO {
	private Integer id;
	private StatusDTO status;
	private String opis;
	private MedSestraDTO sestra;
	private LekarDTO lekar;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public StatusDTO getStatus() {
		return status;
	}

	public void setStatus(StatusDTO status) {
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
	public MedSestraDTO getSestra() {
		return sestra;
	}

	public void setSestra(MedSestraDTO sestra) {
		this.sestra = sestra;
	}

	public LekarDTO getLekar() {
		return lekar;
	}

	public void setLekar(LekarDTO lekar) {
		this.lekar = lekar;
	}

	private Date pocetak;
	private Date kraj;

}