package model;
import java.util.*;

public class Recept {
   public MedSestra medSestra;
   public java.util.Collection<Lek> lek;
   
   
   public java.util.Collection<Lek> getLek() {
      if (lek == null)
         lek = new java.util.HashSet<Lek>();
      return lek;
   }
   
   public java.util.Iterator getIteratorLek() {
      if (lek == null)
         lek = new java.util.HashSet<Lek>();
      return lek.iterator();
   }
   
   public void setLek(java.util.Collection<Lek> newLek) {
      removeAllLek();
      for (java.util.Iterator iter = newLek.iterator(); iter.hasNext();)
         addLek((Lek)iter.next());
   }
   
   public void addLek(Lek newLek) {
      if (newLek == null)
         return;
      if (this.lek == null)
         this.lek = new java.util.HashSet<Lek>();
      if (!this.lek.contains(newLek))
         this.lek.add(newLek);
   }
   
   public void removeLek(Lek oldLek) {
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

}