package dto;
import java.util.*;

public class OperacijaDTO {
   private Date datumVreme;
   private int trajanje;
   private StatusDTO status;
   
   public Set<StavkaCenovnikaDTO> stavkaCenovnika;
   public Set<SalaDTO> sala;
   public Set<LekarDTO> lekar;
   
   
   public Set<StavkaCenovnikaDTO> getStavkaCenovnika() {
      if (stavkaCenovnika == null)
         stavkaCenovnika = new java.util.HashSet<StavkaCenovnikaDTO>();
      return stavkaCenovnika;
   }
   
   public void setStavkaCenovnika(Set<StavkaCenovnikaDTO> newStavkaCenovnika) {
      this.stavkaCenovnika = newStavkaCenovnika;
   }
   
   public void addStavkaCenovnika(StavkaCenovnikaDTO newStavkaCenovnika) {
      if (newStavkaCenovnika == null)
         return;
      if (this.stavkaCenovnika == null)
         this.stavkaCenovnika = new java.util.HashSet<StavkaCenovnikaDTO>();
      if (!this.stavkaCenovnika.contains(newStavkaCenovnika))
         this.stavkaCenovnika.add(newStavkaCenovnika);
   }
   
   public void removeStavkaCenovnika(StavkaCenovnikaDTO oldStavkaCenovnika) {
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
   public Set<SalaDTO> getSala() {
      if (sala == null)
         sala = new java.util.HashSet<SalaDTO>();
      return sala;
   }
   
   public void setSala(Set<SalaDTO> newSala) {
      this.sala = newSala;
   }
   
   public void addSala(SalaDTO newSala) {
      if (newSala == null)
         return;
      if (this.sala == null)
         this.sala = new java.util.HashSet<SalaDTO>();
      if (!this.sala.contains(newSala))
         this.sala.add(newSala);
   }
   
   public void removeSala(SalaDTO oldSala) {
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

public StatusDTO getStatus() {
	return status;
}

public void setStatus(StatusDTO status) {
	this.status = status;
}

public Set<LekarDTO> getLekar() {
	return lekar;
}

public void setLekar(Set<LekarDTO> lekar) {
	this.lekar = lekar;
}

}