package model;
import java.util.*;

public class AdminKlinike extends Korisnik {
   public java.util.Collection<Odsustvo> odsustvo;
   public Klinika klinika;
   
   
   public java.util.Collection<Odsustvo> getOdsustvo() {
      if (odsustvo == null)
         odsustvo = new java.util.HashSet<Odsustvo>();
      return odsustvo;
   }
   
   public java.util.Iterator getIteratorOdsustvo() {
      if (odsustvo == null)
         odsustvo = new java.util.HashSet<Odsustvo>();
      return odsustvo.iterator();
   }
   
   public void setOdsustvo(java.util.Collection<Odsustvo> newOdsustvo) {
      removeAllOdsustvo();
      for (java.util.Iterator iter = newOdsustvo.iterator(); iter.hasNext();)
         addOdsustvo((Odsustvo)iter.next());
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