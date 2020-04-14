package model;
import java.util.*;

public class Pregled {
   private Date datumVreme;
   private int trajanje;
   private Double popust;
   private Status status;
   
   public java.util.Collection<StavkaCenovnika> stavkaCenovnika;
   public Lekar lekar;
   public Sala sala;
   public TipPregleda tipPregleda;
   public Izvestaj izvestaj;
   public java.util.Collection<Lek> lek;
   public java.util.Collection<Dijagnoza> dijagnoza;
   
   
   public java.util.Collection<StavkaCenovnika> getStavkaCenovnika() {
      if (stavkaCenovnika == null)
         stavkaCenovnika = new java.util.HashSet<StavkaCenovnika>();
      return stavkaCenovnika;
   }
   
   public java.util.Iterator getIteratorStavkaCenovnika() {
      if (stavkaCenovnika == null)
         stavkaCenovnika = new java.util.HashSet<StavkaCenovnika>();
      return stavkaCenovnika.iterator();
   }
   
   public void setStavkaCenovnika(java.util.Collection<StavkaCenovnika> newStavkaCenovnika) {
      removeAllStavkaCenovnika();
      for (java.util.Iterator iter = newStavkaCenovnika.iterator(); iter.hasNext();)
         addStavkaCenovnika((StavkaCenovnika)iter.next());
   }
   
   public void addStavkaCenovnika(StavkaCenovnika newStavkaCenovnika) {
      if (newStavkaCenovnika == null)
         return;
      if (this.stavkaCenovnika == null)
         this.stavkaCenovnika = new java.util.HashSet<StavkaCenovnika>();
      if (!this.stavkaCenovnika.contains(newStavkaCenovnika))
         this.stavkaCenovnika.add(newStavkaCenovnika);
   }
   
   public void removeStavkaCenovnika(StavkaCenovnika oldStavkaCenovnika) {
      if (oldStavkaCenovnika == null)
         return;
      if (this.stavkaCenovnika != null)
         if (this.stavkaCenovnika.contains(oldStavkaCenovnika))
            this.stavkaCenovnika.remove(oldStavkaCenovnika);
   }
   
   public void removeAllStavkaCenovnika() {
      if (stavkaCenovnika != null)
         stavkaCenovnika.clear();
   }
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
   public java.util.Collection<Dijagnoza> getDijagnoza() {
      if (dijagnoza == null)
         dijagnoza = new java.util.HashSet<Dijagnoza>();
      return dijagnoza;
   }
   
   public java.util.Iterator getIteratorDijagnoza() {
      if (dijagnoza == null)
         dijagnoza = new java.util.HashSet<Dijagnoza>();
      return dijagnoza.iterator();
   }
   
   public void setDijagnoza(java.util.Collection<Dijagnoza> newDijagnoza) {
      removeAllDijagnoza();
      for (java.util.Iterator iter = newDijagnoza.iterator(); iter.hasNext();)
         addDijagnoza((Dijagnoza)iter.next());
   }
   
   public void addDijagnoza(Dijagnoza newDijagnoza) {
      if (newDijagnoza == null)
         return;
      if (this.dijagnoza == null)
         this.dijagnoza = new java.util.HashSet<Dijagnoza>();
      if (!this.dijagnoza.contains(newDijagnoza))
         this.dijagnoza.add(newDijagnoza);
   }
   
   public void removeDijagnoza(Dijagnoza oldDijagnoza) {
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