package main.mrs.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity

public class Pacijent extends Korisnik {
	
	@Column(name="enabled")
	private boolean aktivan;
	
	@Column(name="lbo", unique=true, nullable=false)
	private String lbo;
	
	@OneToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	public ZKarton zKarton;
	
	@OneToMany(mappedBy="pacijent",fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	public Set<Pregled> pregled;

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public ZKarton getzKarton() {
		return zKarton;
	}

	public void setzKarton(ZKarton zKarton) {
		this.zKarton = zKarton;
	}

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