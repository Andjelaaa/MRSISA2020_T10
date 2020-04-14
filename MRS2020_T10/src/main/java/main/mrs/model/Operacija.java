package main.mrs.model;
import java.util.*;

public class Operacija {
   private Date datumVreme;
   private int trajanje;
   private Status status;
   
   public Set<StavkaCenovnika> stavkaCenovnika;
   public Set<Sala> sala;
   public Set<Lekar> lekar;
   
   
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
   public Set<Sala> getSala() {
      if (sala == null)
         sala = new java.util.HashSet<Sala>();
      return sala;
   }
   
   public void setSala(Set<Sala> newSala) {
      this.sala = newSala;
   }
   
   public void addSala(Sala newSala) {
      if (newSala == null)
         return;
      if (this.sala == null)
         this.sala = new java.util.HashSet<Sala>();
      if (!this.sala.contains(newSala))
         this.sala.add(newSala);
   }
   
   public void removeSala(Sala oldSala) {
      if (oldSala == null)
         return;
      if (this.sala != null)
         if (this.sala.contains(oldSala))
            this.sala.remove(oldSala);
   }
   
   public void removeAllSala() {
      if (sala != null)
         sala.clear();
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

public Status getStatus() {
	return status;
}

public void setStatus(Status status) {
	this.status = status;
}

public Set<Lekar> getLekar() {
	return lekar;
}

public void setLekar(Set<Lekar> lekar) {
	this.lekar = lekar;
}

}