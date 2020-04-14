package dto;
import java.util.HashSet;
import java.util.Set;

public class AdminKCDTO extends KorisnikDTO {
   public Set<ZahtevRegDTO> zahtevReg;
   public Set<KlinickiCentarDTO> klinickiCentar;
   
   
   public Set<ZahtevRegDTO> getZahtevReg() {
      if (zahtevReg == null)
         zahtevReg = new HashSet<ZahtevRegDTO>();
      return zahtevReg;
   }

   
   public void setZahtevReg(Set<ZahtevRegDTO> newZahtevReg) {
      this.zahtevReg = newZahtevReg;
   }
   

   public void addZahtevReg(ZahtevRegDTO newZahtevReg) {
      if (newZahtevReg == null)
         return;
      if (this.zahtevReg == null)
         this.zahtevReg = new HashSet<ZahtevRegDTO>();
      if (!this.zahtevReg.contains(newZahtevReg))
         this.zahtevReg.add(newZahtevReg);
   }
   
  
   public void removeZahtevReg(ZahtevRegDTO oldZahtevReg) {
      if (oldZahtevReg == null)
         return;
      if (this.zahtevReg != null)
         if (this.zahtevReg.contains(oldZahtevReg))
            this.zahtevReg.remove(oldZahtevReg);
   }
   
   public void removeAllZahtevReg() {
      if (zahtevReg != null)
         zahtevReg.clear();
   }
   public Set<KlinickiCentarDTO> getKlinickiCentar() {
      if (klinickiCentar == null)
         klinickiCentar = new HashSet<KlinickiCentarDTO>();
      return klinickiCentar;
   }

   public void setKlinickiCentar(Set<KlinickiCentarDTO> newKlinickiCentar) {
      this.klinickiCentar = newKlinickiCentar;
   }
   
   public void addKlinickiCentar(KlinickiCentarDTO newKlinickiCentar) {
      if (newKlinickiCentar == null)
         return;
      if (this.klinickiCentar == null)
         this.klinickiCentar = new HashSet<KlinickiCentarDTO>();
      if (!this.klinickiCentar.contains(newKlinickiCentar))
         this.klinickiCentar.add(newKlinickiCentar);
   }
   
   public void removeKlinickiCentar(KlinickiCentarDTO oldKlinickiCentar) {
      if (oldKlinickiCentar == null)
         return;
      if (this.klinickiCentar != null)
         if (this.klinickiCentar.contains(oldKlinickiCentar))
            this.klinickiCentar.remove(oldKlinickiCentar);
   }
   
   public void removeAllKlinickiCentar() {
      if (klinickiCentar != null)
         klinickiCentar.clear();
   }

}