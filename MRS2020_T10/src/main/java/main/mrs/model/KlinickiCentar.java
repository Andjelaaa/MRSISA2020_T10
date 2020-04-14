package main.mrs.model;
import java.util.*;

public class KlinickiCentar {
   public Set<Klinika> klinika;
   
   
   public Set<Klinika> getKlinika() {
      if (klinika == null)
         klinika = new HashSet<Klinika>();
      return klinika;
   }

   public void setKlinika(Set<Klinika> newKlinika) {
      this.klinika = newKlinika;
   }
   
   public void addKlinika(Klinika newKlinika) {
      if (newKlinika == null)
         return;
      if (this.klinika == null)
         this.klinika = new HashSet<Klinika>();
      if (!this.klinika.contains(newKlinika))
         this.klinika.add(newKlinika);
   }
   
   public void removeKlinika(Klinika oldKlinika) {
      if (oldKlinika == null)
         return;
      if (this.klinika != null)
         if (this.klinika.contains(oldKlinika))
            this.klinika.remove(oldKlinika);
   }
   
   public void removeAllKlinika() {
      if (klinika != null)
         klinika.clear();
   }

}