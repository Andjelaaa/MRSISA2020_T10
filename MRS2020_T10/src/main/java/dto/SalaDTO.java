package dto;

import java.util.*;

public class SalaDTO {
	private int broj;
	private String naziv;

	public Set<PregledDTO> pregled;

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

	public Set<PregledDTO> getPregled() {
		return pregled;
	}

	public void setPregled(Set<PregledDTO> pregled) {
		this.pregled = pregled;
	}

}