package main.mrs.dto;

import java.util.*;

public class PacijentDTO extends KorisnikDTO {
	private String lbo;
	public ZKartonDTO zKarton;
	public Set<PregledDTO> pregled;

	public String getLbo() {
		return lbo;
	}

	public void setLbo(String lbo) {
		this.lbo = lbo;
	}

	public Set<PregledDTO> getPregled() {
		if (pregled == null)
			pregled = new java.util.HashSet<PregledDTO>();
		return pregled;
	}

	public void setPregled(Set<PregledDTO> newPregled) {
		this.pregled = newPregled;
	}

	public void addPregled(PregledDTO newPregled) {
		if (newPregled == null)
			return;
		if (this.pregled == null)
			this.pregled = new java.util.HashSet<PregledDTO>();
		if (!this.pregled.contains(newPregled))
			this.pregled.add(newPregled);
	}

	public void removePregled(PregledDTO oldPregled) {
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