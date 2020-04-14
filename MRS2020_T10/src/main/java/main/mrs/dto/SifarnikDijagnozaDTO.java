package main.mrs.dto;
import java.util.*;

public class SifarnikDijagnozaDTO {
   public Set<LekDTO> lek;
   public Set<DijagnozaDTO> dijagnoza;
   
   
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

}