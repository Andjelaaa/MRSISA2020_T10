package main.mrs.dto;
import java.util.*;



/** Lekar ima recnik pregleda, kljuc je datum, vrednost pregled
 * 
 *  */
public class LekarDTO extends KorisnikDTO {
   private Double prosecnaOcena;
   private int brojOcena;
   private Date rvPocetak;
   private Date rvKraj;
   
   public TipPregledaDTO tipPregleda;
   public Set<OperacijaDTO> operacija;
   public Set<OdsustvoDTO> odsustvo;
   public KlinikaDTO klinika;
   public KlinikaDTO getKlinika() {
	return klinika;
	}
	
	public void setKlinika(KlinikaDTO klinika) {
		this.klinika = klinika;
	}
	
	public Set<PregledDTO> pregled;
	  
   
   public Set<OperacijaDTO> getOperacija() {
      if (operacija == null)
         operacija = new java.util.HashSet<OperacijaDTO>();
      return operacija;
   }
   
   public void setOperacija(Set<OperacijaDTO> newOperacija) {
      this.operacija = newOperacija;
   }
   
   public void addOperacija(OperacijaDTO newOperacija) {
      if (newOperacija == null)
         return;
      if (this.operacija == null)
         this.operacija = new java.util.HashSet<OperacijaDTO>();
      if (!this.operacija.contains(newOperacija))
         this.operacija.add(newOperacija);
   }
   
   public void removeOperacija(OperacijaDTO oldOperacija) {
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
   public Set<OdsustvoDTO> getOdsustvo() {
      if (odsustvo == null)
         odsustvo = new java.util.HashSet<OdsustvoDTO>();
      return odsustvo;
   }
   
   public void setOdsustvo(Set<OdsustvoDTO> newOdsustvo) {
      this.odsustvo = newOdsustvo;
   }
   
   public void addOdsustvo(OdsustvoDTO newOdsustvo) {
      if (newOdsustvo == null)
         return;
      if (this.odsustvo == null)
         this.odsustvo = new java.util.HashSet<OdsustvoDTO>();
      if (!this.odsustvo.contains(newOdsustvo))
         this.odsustvo.add(newOdsustvo);
   }
   
   public void removeOdsustvo(OdsustvoDTO oldOdsustvo) {
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

public TipPregledaDTO getTipPregleda() {
	return tipPregleda;
}

public void setTipPregleda(TipPregledaDTO tipPregleda) {
	this.tipPregleda = tipPregleda;
}

public Set<PregledDTO> getPregled() {
	return pregled;
}

public void setPregled(Set<PregledDTO> pregled) {
	this.pregled = pregled;
}

}