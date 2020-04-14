package main.mrs.model;
import java.util.*;

public class AdminKlinike extends Korisnik {
   public Set<Odsustvo> odsustvo;
   public Klinika klinika;
   
   
   public Set<Odsustvo> getOdsustvo() {
      if (odsustvo == null)
         odsustvo = new HashSet<Odsustvo>();
      return odsustvo;
   }
   
   public void setOdsustvo(Set<Odsustvo> newOdsustvo) {
      this.odsustvo = newOdsustvo;
   }
   
   public void addOdsustvo(Odsustvo newOdsustvo) {
      if (newOdsustvo == null)
         return;
      if (this.odsustvo == null)
         this.odsustvo = new HashSet<Odsustvo>();
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
   public Klinika getKlinika() {
      return klinika;
   }
   
   public void setKlinika(Klinika newKlinika) {
      if (this.klinika == null || !this.klinika.equals(newKlinika))
      {
         if (this.klinika != null)
         {
            Klinika oldKlinika = this.klinika;
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