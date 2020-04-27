package main.mrs.model;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** Lekar ima recnik pregleda, kljuc je datum, vrednost pregled
 * 
 *  */
@Entity
@Table(name="Lekar")
public class Lekar extends Korisnik {
	
   @Column(name="prosecnaOcena", unique=false, nullable=true)
   private Double prosecnaOcena;
   
   @Column(name="brojOcena", unique=false, nullable=true)
   private int brojOcena;
   
   @Column(name="rvPocetak", unique=false, nullable=false)
   private String rvPocetak;
   
   @Column(name="rvKraj", unique=false, nullable=false)
   private String rvKraj;
   
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public TipPregleda tipPregleda;

   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<Operacija> operacija;
   
   @OneToMany(mappedBy="lekar",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<Odsustvo> odsustvo;
   
   @OneToMany(mappedBy="lekar",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<Pregled> pregled;
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Klinika klinika;
   
   public Set<Pregled> getPregled() {
	return pregled;
}

public void setPregled(Set<Pregled> pregled) {
	this.pregled = pregled;
}

public Klinika getKlinika() {
	return klinika;
}

public void setKlinika(Klinika klinika) {
	this.klinika = klinika;
}

public Set<Operacija> getOperacija() {
      if (operacija == null)
         operacija = new java.util.HashSet<Operacija>();
      return operacija;
   }
   
   public void setOperacija(Set<Operacija> newOperacija) {
      this.operacija = newOperacija;
   }
   
   public void addOperacija(Operacija newOperacija) {
      if (newOperacija == null)
         return;
      if (this.operacija == null)
         this.operacija = new java.util.HashSet<Operacija>();
      if (!this.operacija.contains(newOperacija))
         this.operacija.add(newOperacija);
   }
   
   public void removeOperacija(Operacija oldOperacija) {
      if (oldOperacija == null)
         return;
      if (this.operacija != null)
         if (this.operacija.contains(oldOperacija))
            this.operacija.remove(oldOperacija);
   }
   
   public void removeAllOperacija() {
      if (operacija != null)
         operacija.clear();
   }
   public Set<Odsustvo> getOdsustvo() {
      if (odsustvo == null)
         odsustvo = new java.util.HashSet<Odsustvo>();
      return odsustvo;
   }
   
   public void setOdsustvo(Set<Odsustvo> newOdsustvo) {
      this.odsustvo = newOdsustvo;
   }
   
   public void addOdsustvo(Odsustvo newOdsustvo) {
      if (newOdsustvo == null)
         return;
      if (this.odsustvo == null)
         this.odsustvo = new java.util.HashSet<Odsustvo>();
      if (!this.odsustvo.contains(newOdsustvo))
         this.odsustvo.add(newOdsustvo);
   }
   
   public void removeOdsustvo(Odsustvo oldOdsustvo) {
      if (oldOdsustvo == null)
         return;
      if (this.odsustvo != null)
         if (this.odsustvo.contains(oldOdsustvo))
            this.odsustvo.remove(oldOdsustvo);
   }
   
   public void removeAllOdsustvo() {
      if (odsustvo != null)
         odsustvo.clear();
   }

public Double getProsecnaOcena() {
	return prosecnaOcena;
}

public void setProsecnaOcena(Double prosecnaOcena) {
	this.prosecnaOcena = prosecnaOcena;
}

public int getBrojOcena() {
	return brojOcena;
}

public void setBrojOcena(int brojOcena) {
	this.brojOcena = brojOcena;
}

public String getRvPocetak() {
	return rvPocetak;
}

public void setRvPocetak(String rvPocetak) {
	this.rvPocetak = rvPocetak;
}

public String getRvKraj() {
	return rvKraj;
}

public void setRvKraj(String rvKraj) {
	this.rvKraj = rvKraj;
}

public TipPregleda getTipPregleda() {
	return tipPregleda;
}

public void setTipPregleda(TipPregleda tipPregleda) {
	this.tipPregleda = tipPregleda;
}

//public Set<Pregled> getPregled() {
//	return pregled;
//}
//
//public void setPregled(Set<Pregled> pregled) {
//	this.pregled = pregled;
//}

}