package model;

import java.util.*;

public class Pacijent extends Korisnik {
	private String lbo;
	public ZKarton zKarton;
	public Set<Pregled> pregled;

	public String getLbo() {
		return lbo;
	}

	public void setLbo(String lbo) {
		this.lbo = lbo;
	}

	public Set<Pregled> getPregled() {
		if (pregled == null)
			pregled = new java.util.HashSet<Pregled>();
		return pregled;
	}

	public void setPregled(Set<Pregled> newPregled) {
		this.pregled = newPregled;
	}

	public void addPregled(Pregled newPregled) {
		if (newPregled == null)
			return;
		if (this.pregled == null)
			this.pregled = new java.util.HashSet<Pregled>();
		if (!this.pregled.contains(newPregled))
			this.pregled.add(newPregled);
	}

	public void removePregled(Pregled oldPregled) {
		if (oldPregled == null)
			return;
		if (this.pregled != null)
			if (this.pregled.contains(oldPregled))
				this.pregled.remove(oldPregled);
	}

	public void removeAllPregled() {
		if (pregled != null)
			pregled.clear();
	}

}