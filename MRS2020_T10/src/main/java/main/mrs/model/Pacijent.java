package main.mrs.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Entity
//@Table(name="PACIJENT")

public class Pacijent extends Korisnik {
	
	//@Column(name="lbo", unique=true, nullable=false)
	private String lbo;
	
	//@OneToOne(fetch= FetchType.LAZY)
	//@JoinColumn(name= "")
	public ZKarton zKarton;
	
	
	//@Column(name="pregled", unique=false, nullable=false)
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