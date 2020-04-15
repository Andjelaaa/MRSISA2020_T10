package main.mrs.dto;

import main.mrs.model.StavkaCenovnika;

public class TipPregledaDTO {
	
	
	private String naziv;
	private String opis;
	
    private int brojAktvnih;
    private StavkaCenovnikaDTO stavka;

	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getBrojAktvnih() {
		return brojAktvnih;
	}

	public void setBrojAktvnih(int brojAktvnih) {
		this.brojAktvnih = brojAktvnih;
}

	public StavkaCenovnikaDTO getStavka() {
		return stavka;
	}

	public void setStavka(StavkaCenovnikaDTO stavka) {
		this.stavka = stavka;
	}

}