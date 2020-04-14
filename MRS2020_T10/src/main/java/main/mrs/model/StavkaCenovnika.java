package main.mrs.model;

import java.util.*;

public class StavkaCenovnika {
	private double cena;

	public Set<TipPregleda> tipPregleda;

	public Set<TipPregleda> getTipPregleda() {
		if (tipPregleda == null)
			tipPregleda = new java.util.HashSet<TipPregleda>();
		return tipPregleda;
	}

	public void setTipPregleda(Set<TipPregleda> newTipPregleda) {
		this.tipPregleda = newTipPregleda;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public void addTipPregleda(TipPregleda newTipPregleda) {
		if (newTipPregleda == null)
			return;
		if (this.tipPregleda == null)
			this.tipPregleda = new java.util.HashSet<TipPregleda>();
		if (!this.tipPregleda.contains(newTipPregleda))
			this.tipPregleda.add(newTipPregleda);
	}

	public void removeTipPregleda(TipPregleda oldTipPregleda) {
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