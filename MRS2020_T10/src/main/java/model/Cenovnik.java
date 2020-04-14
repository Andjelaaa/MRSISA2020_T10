package model;
import java.util.*;

public class Cenovnik {
   public java.util.Collection<StavkaCenovnika> stavkaCenovnika;
   
   
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

}