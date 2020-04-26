package main.mrs.dto;

import java.util.*;

import main.mrs.model.Cenovnik;

public class StavkaCenovnikaDTO {
	private Integer id;
	private double cena;
	public CenovnikDTO cenovnik;

	public CenovnikDTO getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(CenovnikDTO cenovnik) {
		this.cenovnik = cenovnik;
	}

	public Set<TipPregledaDTO> tipPregleda;

	public Set<TipPregledaDTO> getTipPregleda() {
		if (tipPregleda == null)
			tipPregleda = new java.util.HashSet<TipPregledaDTO>();
		return tipPregleda;
	}

	public void setTipPregleda(Set<TipPregledaDTO> newTipPregleda) {
		this.tipPregleda = newTipPregleda;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public void addTipPregleda(TipPregledaDTO newTipPregleda) {
		if (newTipPregleda == null)
			return;
		if (this.tipPregleda == null)
			this.tipPregleda = new java.util.HashSet<TipPregledaDTO>();
		if (!this.tipPregleda.contains(newTipPregleda))
			this.tipPregleda.add(newTipPregleda);
	}

	public void removeTipPregleda(TipPregledaDTO oldTipPregleda) {
		if (oldTipPregleda == null)
			return;
		if (this.tipPregleda != null)
			if (this.tipPregleda.contains(oldTipPregleda))
				this.tipPregleda.remove(oldTipPregleda);
	}

	public void removeAllTipPregleda() {
		if (tipPregleda != null)
			tipPregleda.clear();
	}

}