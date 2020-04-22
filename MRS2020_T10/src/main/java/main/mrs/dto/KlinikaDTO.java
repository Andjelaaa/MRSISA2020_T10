package main.mrs.dto;
import java.util.*;

public class KlinikaDTO {
	private Integer id;
   private String naziv;
   private String adresa;
   private String opis;
   private Double prosecnaOcena;
   private int brojOcena;
   
   public Set<PacijentDTO> pacijent;
   public Set<PregledDTO> pregled;
   public CenovnikDTO cenovnik;
   public Set<AdminKlinikeDTO> adminKlinike;
   public Set<OperacijaDTO> operacija;
   public Set<LekarDTO> lekar;
   public Set<MedSestraDTO> medSestra;
   
   public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
   
   public CenovnikDTO getCenovnik() {
	return cenovnik;
}

public void setCenovnik(CenovnikDTO cenovnik) {
	this.cenovnik = cenovnik;
}

public Set<PacijentDTO> getpacijent() {
      if (pacijent == null)
         pacijent = new java.util.HashSet<PacijentDTO>();
      return pacijent;
   }
   
   public void setPacijent(Set<PacijentDTO> newpacijent) {
      this.pacijent = newpacijent;
   }
   
   public void addpacijent(PacijentDTO newpacijent) {
      if (newpacijent == null)
         return;
      if (this.pacijent == null)
         this.pacijent = new java.util.HashSet<PacijentDTO>();
      if (!this.pacijent.contains(newpacijent))
         this.pacijent.add(newpacijent);
   }
   
   public void removepacijent(PacijentDTO oldpacijent) {
      if (oldpacijent == null)
         return;
      if (this.pacijent != null)
         if (this.pacijent.contains(oldpacijent))
            this.pacijent.remove(oldpacijent);
   }
   
   public void removeAllpacijent() {
      if (pacijent != null)
         pacijent.clear();
   }
   public Set<PregledDTO> getPregled() {
      if (pregled == null)
         pregled = new java.util.HashSet<PregledDTO>();
      return pregled;
   }
   
   public void setPregled(Set<PregledDTO> newPregled) {
      this.pregled = newPregled;
   }
   
   public void addPregled(PregledDTO newPregled) {
      if (newPregled == null)
         return;
      if (this.pregled == null)
         this.pregled = new java.util.HashSet<PregledDTO>();
      if (!this.pregled.contains(newPregled))
         this.pregled.add(newPregled);
   }
   
   public void removePregled(PregledDTO oldPregled) {
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

   
   public Set<AdminKlinikeDTO> getAdminKlinike() {
      if (adminKlinike == null)
         adminKlinike = new java.util.HashSet<AdminKlinikeDTO>();
      return adminKlinike;
   }
   
   public void setAdminKlinike(Set<AdminKlinikeDTO> newAdminKlinike) {
      this.adminKlinike = newAdminKlinike;
   }
   
   public void addAdminKlinike(AdminKlinikeDTO newAdminKlinike) {
      if (newAdminKlinike == null)
         return;
      if (this.adminKlinike == null)
         this.adminKlinike = new java.util.HashSet<AdminKlinikeDTO>();
      if (!this.adminKlinike.contains(newAdminKlinike))
      {
         this.adminKlinike.add(newAdminKlinike);
         newAdminKlinike.setKlinika(this);      
      }
   }
   
   public void removeAdminKlinike(AdminKlinikeDTO oldAdminKlinike) {
      if (oldAdminKlinike == null)
         return;
      if (this.adminKlinike != null)
         if (this.adminKlinike.contains(oldAdminKlinike))
         {
            this.adminKlinike.remove(oldAdminKlinike);
            oldAdminKlinike.setKlinika((KlinikaDTO)null);
         }
   }

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
   public Set<LekarDTO> getLekar() {
      if (lekar == null)
         lekar = new java.util.HashSet<LekarDTO>();
      return lekar;
   }
   
   public void setLekar(Set<LekarDTO> newLekar) {
      this.lekar = newLekar;
   }
   
   public void addLekar(LekarDTO newLekar) {
      if (newLekar == null)
         return;
      if (this.lekar == null)
         this.lekar = new java.util.HashSet<LekarDTO>();
      if (!this.lekar.contains(newLekar))
         this.lekar.add(newLekar);
   }
   
   public void removeLekar(LekarDTO oldLekar) {
      if (oldLekar == null)
         return;
      if (this.lekar != null)
         if (this.lekar.contains(oldLekar))
            this.lekar.remove(oldLekar);
   }
   
   public void removeAllLekar() {
      if (lekar != null)
         lekar.clear();
   }
   public Set<MedSestraDTO> getMedSestra() {
      if (medSestra == null)
         medSestra = new java.util.HashSet<MedSestraDTO>();
      return medSestra;
   }

   public void setMedSestra(Set<MedSestraDTO> newMedSestra) {
      this.medSestra = newMedSestra;
   }
   
   public void addMedSestra(MedSestraDTO newMedSestra) {
      if (newMedSestra == null)
         return;
      if (this.medSestra == null)
         this.medSestra = new java.util.HashSet<MedSestraDTO>();
      if (!this.medSestra.contains(newMedSestra))
         this.medSestra.add(newMedSestra);
   }
   
   public void removeMedSestra(MedSestraDTO oldMedSestra) {
      if (oldMedSestra == null)
         return;
      if (this.medSestra != null)
         if (this.medSestra.contains(oldMedSestra))
            this.medSestra.remove(oldMedSestra);
   }
   
   public void removeAllMedSestra() {
      if (medSestra != null)
         medSestra.clear();
   }

public String getNaziv() {
	return naziv;
}

public void setNaziv(String naziv) {
	this.naziv = naziv;
}

public String getAdresa() {
	return adresa;
}

public void setAdresa(String adresa) {
	this.adresa = adresa;
}

public String getOpis() {
	return opis;
}

public void setOpis(String opis) {
	this.opis = opis;
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

public Set<PacijentDTO> getPacijent() {
	return pacijent;
}

}