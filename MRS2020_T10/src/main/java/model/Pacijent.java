package model;
import java.util.*;

public class Pacijent extends Korisnik {
   private String lbo;
   
   public ZKarton zKarton;
   public java.util.Collection<Pregled> pregled;
   
   
   public java.util.Collection<Pregled> getPregled() {
      if (pregled == null)
         pregled = new java.util.HashSet<Pregled>();
      return pregled;
   }
   
   public java.util.Iterator getIteratorPregled() {
      if (pregled == null)
         pregled = new java.util.HashSet<Pregled>();
      return pregled.iterator();
   }
   
   public void setPregled(java.util.Collection<Pregled> newPregled) {
      removeAllPregled();
      for (java.util.Iterator iter = newPregled.iterator(); iter.hasNext();)
         addPregled((Pregled)iter.next());
   }
   
   public void addPregled(Pregled newPregled) {
      if (newPregled == null)
         return;
      if (this.pregled == null)
         this.pregled = new java.util.HashSet<Pregled>();
      if (!this.pregled.contains(newPregled))
         this.pregled.add(newPregled);
   }
   
   public void removePregled(Pregled oldPregled) {
      if (oldPregled == null)
         return;
      if (this.pregled != null)
         if (this.pregled.contains(oldPregled))
            this.pregled.remove(oldPregled);
   }
   
   public void removeAllPregled() {
      if (pregled != null)
         pregled.clear();
   }

}