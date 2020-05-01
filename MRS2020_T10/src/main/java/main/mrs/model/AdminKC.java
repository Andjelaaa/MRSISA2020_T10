package main.mrs.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class AdminKC extends Korisnik {
	
	
//	@OneToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
//	@JoinColumn(name="adminKC_id", nullable=true)
//	public Set<ZahtevReg> zahtevReg;
   
	@OneToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    public KlinickiCentar klinickiCentar;


	public KlinickiCentar getKlinickiCentar() {
		return klinickiCentar;
	}
	
	
	public void setKlinickiCentar(KlinickiCentar klinickiCentar) {
		this.klinickiCentar = klinickiCentar;
	}
	   
	}