package main.mrs.dto;
import java.util.*;

public class AdminKlinikeDTO extends KorisnikDTO {
   public Set<OdsustvoDTO> odsustvo;
   public KlinikaDTO klinika;
   
   
   public Set<OdsustvoDTO> getOdsustvo() {
      if (odsustvo == null)
         odsustvo = new HashSet<OdsustvoDTO>();
      return odsustvo;
   }
   
   public void setOdsustvo(Set<OdsustvoDTO> newOdsustvo) {
      this.odsustvo = newOdsustvo;
   }
   
   public void addOdsustvo(OdsustvoDTO newOdsustvo) {
      if (newOdsustvo == null)
         return;
      if (this.odsustvo == null)
         this.odsustvo = new HashSet<OdsustvoDTO>();
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
   public KlinikaDTO getKlinika() {
      return klinika;
   }
   
   public void setKlinika(KlinikaDTO newKlinika) {
      if (this.klinika == null || !this.klinika.equals(newKlinika))
      {
         if (this.klinika != null)
         {
            KlinikaDTO oldKlinika = this.klinika;
            this.klinika = null;
            oldKlinika.removeAdminKlinike(this);
         }
         if (newKlinika != null)
         {
            this.klinika = newKlinika;
            this.klinika.addAdminKlinike(this);
         }
      }
   }

}