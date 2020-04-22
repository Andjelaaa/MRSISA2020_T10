package main.mrs.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TipPregleda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="naziv", unique=true, nullable=false)
	private String naziv;
	
	@Column(name="opis", unique=false, nullable=false)
	private String opis;
	
	@Column(name="brojAktvnih", unique=false, nullable=false)
    private int brojAktvnih;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private StavkaCenovnika stavka;
	
	public TipPregleda() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public StavkaCenovnika getStavka() {
		return stavka;
	}

	public void setStavka(StavkaCenovnika stavka) {
		this.stavka = stavka;
	}

	public int getBrojAktvnih() {
		return brojAktvnih;
	}

	public void setBrojAktvnih(int brojAktvnih) {
		this.brojAktvnih = brojAktvnih;
}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

}