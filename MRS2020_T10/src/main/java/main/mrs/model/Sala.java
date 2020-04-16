package main.mrs.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Sala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="broj", unique=false, nullable=false)
	private int broj;
	
	@Column(name="naziv", unique=false, nullable=false)
	private String naziv;

	@OneToMany(mappedBy="sala",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<Pregled> pregled;

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Set<Pregled> getPregled() {
		return pregled;
	}

	public void setPregled(Set<Pregled> pregled) {
		this.pregled = pregled;
	}

}