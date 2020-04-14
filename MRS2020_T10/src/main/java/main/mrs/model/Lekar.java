package main.mrs.model;
import java.util.*;

/** Lekar ima recnik pregleda, kljuc je datum, vrednost pregled
 * 
 *  */
public class Lekar extends Korisnik {
   private Double prosecnaOcena;
   private int brojOcena;
   private Date rvPocetak;
   private Date rvKraj;
   
   public TipPregleda tipPregleda;
   public Set<Operacija> operacija;
   public Set<Odsustvo> odsustvo;
   public Set<Pregled> pregled;
   
   
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

public Date getRvPocetak() {
	return rvPocetak;
}

public void setRvPocetak(Date rvPocetak) {
	this.rvPocetak = rvPocetak;
}

public Date getRvKraj() {
	return rvKraj;
}

public void setRvKraj(Date rvKraj) {
	this.rvKraj = rvKraj;
}

public TipPregleda getTipPregleda() {
	return tipPregleda;
}

public void setTipPregleda(TipPregleda tipPregleda) {
	this.tipPregleda = tipPregleda;
}

public Set<Pregled> getPregled() {
	return pregled;
}

public void setPregled(Set<Pregled> pregled) {
	this.pregled = pregled;
}

}