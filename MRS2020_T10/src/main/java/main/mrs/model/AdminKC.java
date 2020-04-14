package main.mrs.model;
import java.util.HashSet;
import java.util.Set;

public class AdminKC extends Korisnik {
   public Set<ZahtevReg> zahtevReg;
   public Set<KlinickiCentar> klinickiCentar;
   
   
   public Set<ZahtevReg> getZahtevReg() {
      if (zahtevReg == null)
         zahtevReg = new HashSet<ZahtevReg>();
      return zahtevReg;
   }

   
   public void setZahtevReg(Set<ZahtevReg> newZahtevReg) {
      this.zahtevReg = newZahtevReg;
   }
   

   public void addZahtevReg(ZahtevReg newZahtevReg) {
      if (newZahtevReg == null)
         return;
      if (this.zahtevReg == null)
         this.zahtevReg = new HashSet<ZahtevReg>();
      if (!this.zahtevReg.contains(newZahtevReg))
         this.zahtevReg.add(newZahtevReg);
   }
   
  
   public void removeZahtevReg(ZahtevReg oldZahtevReg) {
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
   public Set<KlinickiCentar> getKlinickiCentar() {
      if (klinickiCentar == null)
         klinickiCentar = new HashSet<KlinickiCentar>();
      return klinickiCentar;
   }

   public void setKlinickiCentar(Set<KlinickiCentar> newKlinickiCentar) {
      this.klinickiCentar = newKlinickiCentar;
   }
   
   public void addKlinickiCentar(KlinickiCentar newKlinickiCentar) {
      if (newKlinickiCentar == null)
         return;
      if (this.klinickiCentar == null)
         this.klinickiCentar = new HashSet<KlinickiCentar>();
      if (!this.klinickiCentar.contains(newKlinickiCentar))
         this.klinickiCentar.add(newKlinickiCentar);
   }
   
   public void removeKlinickiCentar(KlinickiCentar oldKlinickiCentar) {
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