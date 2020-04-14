package main.mrs.dto;

import java.util.*;

public class StavkaCenovnikaDTO {
	private double cena;

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