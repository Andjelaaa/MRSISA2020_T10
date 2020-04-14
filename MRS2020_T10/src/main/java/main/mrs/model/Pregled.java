package main.mrs.model;
import java.util.*;

public class Pregled {
   private Date datumVreme;
   private int trajanje;
   private Double popust;
   private Status status;
   
   public Set<StavkaCenovnika> stavkaCenovnika;
   public Lekar lekar;
   public Sala sala;
   public TipPregleda tipPregleda;
   public Izvestaj izvestaj;
   public Set<Lek> lek;
   public Set<Dijagnoza> dijagnoza;
   
   
   public Set<StavkaCenovnika> getStavkaCenovnika() {
      if (stavkaCenovnika == null)
         stavkaCenovnika = new java.util.HashSet<StavkaCenovnika>();
      return stavkaCenovnika;
   }
   
   public void setStavkaCenovnika(Set<StavkaCenovnika> newStavkaCenovnika) {
      this.stavkaCenovnika = newStavkaCenovnika;
   }
   
   public void addStavkaCenovnika(StavkaCenovnika newStavkaCenovnika) {
      if (newStavkaCenovnika == null)
         return;
      if (this.stavkaCenovnika == null)
         this.stavkaCenovnika = new java.util.HashSet<StavkaCenovnika>();
      if (!this.stavkaCenovnika.contains(newStavkaCenovnika))
         this.stavkaCenovnika.add(newStavkaCenovnika);
   }
   
   public void removeStavkaCenovnika(StavkaCenovnika oldStavkaCenovnika) {
      if (oldStavkaCenovnika == null)
         return;
      if (this.stavkaCenovnika != null)
         if (this.stavkaCenovnika.contains(oldStavkaCenovnika))
            this.stavkaCenovnika.remove(oldStavkaCenovnika);
   }
   
   public void removeAllStavkaCenovnika() {
      if (stavkaCenovnika != null)
         stavkaCenovnika.clear();
   }
   public Set<Lek> getLek() {
      if (lek == null)
         lek = new java.util.HashSet<Lek>();
      return lek;
   }
   
   public void setLek(Set<Lek> newLek) {
      this.lek = newLek;
   }
   
   public void addLek(Lek newLek) {
      if (newLek == null)
         return;
      if (this.lek == null)
         this.lek = new java.util.HashSet<Lek>();
      if (!this.lek.contains(newLek))
         this.lek.add(newLek);
   }
   
   public void removeLek(Lek oldLek) {
      if (oldLek == null)
         return;
      if (this.lek != null)
         if (this.lek.contains(oldLek))
            this.lek.remove(oldLek);
   }
   
   public void removeAllLek() {
      if (lek != null)
         lek.clear();
   }
   public Set<Dijagnoza> getDijagnoza() {
      if (dijagnoza == null)
         dijagnoza = new java.util.HashSet<Dijagnoza>();
      return dijagnoza;
   }
   
   public void setDijagnoza(Set<Dijagnoza> newDijagnoza) {
      this.dijagnoza = newDijagnoza;
   }
   
   public void addDijagnoza(Dijagnoza newDijagnoza) {
      if (newDijagnoza == null)
         return;
      if (this.dijagnoza == null)
         this.dijagnoza = new java.util.HashSet<Dijagnoza>();
      if (!this.dijagnoza.contains(newDijagnoza))
         this.dijagnoza.add(newDijagnoza);
   }
   
   public void removeDijagnoza(Dijagnoza oldDijagnoza) {
      if (oldDijagnoza == null)
         return;
      if (this.dijagnoza != null)
         if (this.dijagnoza.contains(oldDijagnoza))
            this.dijagnoza.remove(oldDijagnoza);
   }
   
   public void removeAllDijagnoza() {
      if (dijagnoza != null)
         dijagnoza.clear();
   }

public Date getDatumVreme() {
	return datumVreme;
}

public void setDatumVreme(Date datumVreme) {
	this.datumVreme = datumVreme;
}

public int getTrajanje() {
	return trajanje;
}

public void setTrajanje(int trajanje) {
	this.trajanje = trajanje;
}

public Double getPopust() {
	return popust;
}

public void setPopust(Double popust) {
	this.popust = popust;
}

public Status getStatus() {
	return status;
}

public void setStatus(Status status) {
	this.status = status;
}

public Lekar getLekar() {
	return lekar;
}

public void setLekar(Lekar lekar) {
	this.lekar = lekar;
}

public Sala getSala() {
	return sala;
}

public void setSala(Sala sala) {
	this.sala = sala;
}

public TipPregleda getTipPregleda() {
	return tipPregleda;
}

public void setTipPregleda(TipPregleda tipPregleda) {
	this.tipPregleda = tipPregleda;
}

public Izvestaj getIzvestaj() {
	return izvestaj;
}

public void setIzvestaj(Izvestaj izvestaj) {
	this.izvestaj = izvestaj;
}

}