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
	
	
	@OneToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="adminKC_id", nullable=false)
	public Set<ZahtevReg> zahtevReg;
   
	@OneToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    public KlinickiCentar klinickiCentar;
   
   
   public Set<ZahtevReg> getZahtevReg() {
      if (zahtevReg == null)
         zahtevReg = new HashSet<ZahtevReg>();
      return zahtevReg;
   }

   
   public void setZahtevReg(Set<ZahtevReg> newZahtevReg) {
      this.zahtevReg = newZahtevReg;
   }
   

   public void addZahtevReg(ZahtevReg newZahtevReg) {
      if (newZahtevReg == null)
         return;
      if (this.zahtevReg == null)
         this.zahtevReg = new HashSet<ZahtevReg>();
      if (!this.zahtevReg.contains(newZahtevReg))
         this.zahtevReg.add(newZahtevReg);
   }
   
  
   public void removeZahtevReg(ZahtevReg oldZahtevReg) {
      if (oldZahtevReg == null)
         return;
      if (this.zahtevReg != null)
         if (this.zahtevReg.contains(oldZahtevReg))
            this.zahtevReg.remove(oldZahtevReg);
   }
   
   public void removeAllZahtevReg() {
      if (zahtevReg != null)
         zahtevReg.clear();
   }


	public KlinickiCentar getKlinickiCentar() {
		return klinickiCentar;
	}
	
	
	public void setKlinickiCentar(KlinickiCentar klinickiCentar) {
		this.klinickiCentar = klinickiCentar;
	}
	   
	}