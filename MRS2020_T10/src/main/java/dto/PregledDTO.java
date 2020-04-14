package dto;
import java.util.*;

public class PregledDTO {
   private Date datumVreme;
   private int trajanje;
   private Double popust;
   private StatusDTO status;
   
   public Set<StavkaCenovnikaDTO> stavkaCenovnika;
   public LekarDTO lekar;
   public SalaDTO sala;
   public TipPregledaDTO tipPregleda;
   public IzvestajDTO izvestaj;
   public Set<LekDTO> lek;
   public Set<DijagnozaDTO> dijagnoza;
   
   
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
   public Set<LekDTO> getLek() {
      if (lek == null)
         lek = new java.util.HashSet<LekDTO>();
      return lek;
   }
   
   public void setLek(Set<LekDTO> newLek) {
      this.lek = newLek;
   }
   
   public void addLek(LekDTO newLek) {
      if (newLek == null)
         return;
      if (this.lek == null)
         this.lek = new java.util.HashSet<LekDTO>();
      if (!this.lek.contains(newLek))
         this.lek.add(newLek);
   }
   
   public void removeLek(LekDTO oldLek) {
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
   public Set<DijagnozaDTO> getDijagnoza() {
      if (dijagnoza == null)
         dijagnoza = new java.util.HashSet<DijagnozaDTO>();
      return dijagnoza;
   }
   
   public void setDijagnoza(Set<DijagnozaDTO> newDijagnoza) {
      this.dijagnoza = newDijagnoza;
   }
   
   public void addDijagnoza(DijagnozaDTO newDijagnoza) {
      if (newDijagnoza == null)
         return;
      if (this.dijagnoza == null)
         this.dijagnoza = new java.util.HashSet<DijagnozaDTO>();
      if (!this.dijagnoza.contains(newDijagnoza))
         this.dijagnoza.add(newDijagnoza);
   }
   
   public void removeDijagnoza(DijagnozaDTO oldDijagnoza) {
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

public StatusDTO getStatus() {
	return status;
}

public void setStatus(StatusDTO status) {
	this.status = status;
}

public LekarDTO getLekar() {
	return lekar;
}

public void setLekar(LekarDTO lekar) {
	this.lekar = lekar;
}

public SalaDTO getSala() {
	return sala;
}

public void setSala(SalaDTO sala) {
	this.sala = sala;
}

public TipPregledaDTO getTipPregleda() {
	return tipPregleda;
}

public void setTipPregleda(TipPregledaDTO tipPregleda) {
	this.tipPregleda = tipPregleda;
}

public IzvestajDTO getIzvestaj() {
	return izvestaj;
}

public void setIzvestaj(IzvestajDTO izvestaj) {
	this.izvestaj = izvestaj;
}

}