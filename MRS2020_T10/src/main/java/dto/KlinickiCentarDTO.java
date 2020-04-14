package dto;
import java.util.*;

public class KlinickiCentarDTO {
   public Set<KlinikaDTO> klinika;
   
   
   public Set<KlinikaDTO> getKlinika() {
      if (klinika == null)
         klinika = new HashSet<KlinikaDTO>();
      return klinika;
   }

   public void setKlinika(Set<KlinikaDTO> newKlinika) {
      this.klinika = newKlinika;
   }
   
   public void addKlinika(KlinikaDTO newKlinika) {
      if (newKlinika == null)
         return;
      if (this.klinika == null)
         this.klinika = new HashSet<KlinikaDTO>();
      if (!this.klinika.contains(newKlinika))
         this.klinika.add(newKlinika);
   }
   
   public void removeKlinika(KlinikaDTO oldKlinika) {
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