package model;
import java.util.*;

public class AdminKC extends Korisnik {
   public java.util.Collection<ZahtevReg> zahtevReg;
   public java.util.Collection<KlinickiCentar> klinickiCentar;
   
   
   public java.util.Collection<ZahtevReg> getZahtevReg() {
      if (zahtevReg == null)
         zahtevReg = new java.util.HashSet<ZahtevReg>();
      return zahtevReg;
   }
   
   public java.util.Iterator getIteratorZahtevReg() {
      if (zahtevReg == null)
         zahtevReg = new java.util.HashSet<ZahtevReg>();
      return zahtevReg.iterator();
   }
   
   public void setZahtevReg(java.util.Collection<ZahtevReg> newZahtevReg) {
      removeAllZahtevReg();
      for (java.util.Iterator iter = newZahtevReg.iterator(); iter.hasNext();)
         addZahtevReg((ZahtevReg)iter.next());
   }
   

   public void addZahtevReg(ZahtevReg newZahtevReg) {
      if (newZahtevReg == null)
         return;
      if (this.zahtevReg == null)
         this.zahtevReg = new java.util.HashSet<ZahtevReg>();
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
   public java.util.Collection<KlinickiCentar> getKlinickiCentar() {
      if (klinickiCentar == null)
         klinickiCentar = new java.util.HashSet<KlinickiCentar>();
      return klinickiCentar;
   }
   
   public java.util.Iterator getIteratorKlinickiCentar() {
      if (klinickiCentar == null)
         klinickiCentar = new java.util.HashSet<KlinickiCentar>();
      return klinickiCentar.iterator();
   }
   

   public void setKlinickiCentar(java.util.Collection<KlinickiCentar> newKlinickiCentar) {
      removeAllKlinickiCentar();
      for (java.util.Iterator iter = newKlinickiCentar.iterator(); iter.hasNext();)
         addKlinickiCentar((KlinickiCentar)iter.next());
   }
   
   public void addKlinickiCentar(KlinickiCentar newKlinickiCentar) {
      if (newKlinickiCentar == null)
         return;
      if (this.klinickiCentar == null)
         this.klinickiCentar = new java.util.HashSet<KlinickiCentar>();
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