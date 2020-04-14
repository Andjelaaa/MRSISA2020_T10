package model;
import java.util.*;

public class KlinickiCentar {
   public java.util.Collection<Klinika> klinika;
   
   
   public java.util.Collection<Klinika> getKlinika() {
      if (klinika == null)
         klinika = new java.util.HashSet<Klinika>();
      return klinika;
   }
   
   public java.util.Iterator getIteratorKlinika() {
      if (klinika == null)
         klinika = new java.util.HashSet<Klinika>();
      return klinika.iterator();
   }
   
   public void setKlinika(java.util.Collection<Klinika> newKlinika) {
      removeAllKlinika();
      for (java.util.Iterator iter = newKlinika.iterator(); iter.hasNext();)
         addKlinika((Klinika)iter.next());
   }
   
   public void addKlinika(Klinika newKlinika) {
      if (newKlinika == null)
         return;
      if (this.klinika == null)
         this.klinika = new java.util.HashSet<Klinika>();
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