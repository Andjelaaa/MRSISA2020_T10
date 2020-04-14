package model;
import java.util.*;

public class Operacija {
   private Date datumVreme;
   private int trajanje;
   private Status status;
   
   public java.util.Collection<StavkaCenovnika> stavkaCenovnika;
   public java.util.Collection<Sala> sala;
   public Lekar[] lekar;
   
   
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
   public java.util.Collection<Sala> getSala() {
      if (sala == null)
         sala = new java.util.HashSet<Sala>();
      return sala;
   }
   
   public java.util.Iterator getIteratorSala() {
      if (sala == null)
         sala = new java.util.HashSet<Sala>();
      return sala.iterator();
   }
   
   public void setSala(java.util.Collection<Sala> newSala) {
      removeAllSala();
      for (java.util.Iterator iter = newSala.iterator(); iter.hasNext();)
         addSala((Sala)iter.next());
   }
   
   public void addSala(Sala newSala) {
      if (newSala == null)
         return;
      if (this.sala == null)
         this.sala = new java.util.HashSet<Sala>();
      if (!this.sala.contains(newSala))
         this.sala.add(newSala);
   }
   
   public void removeSala(Sala oldSala) {
      if (oldSala == null)
         return;
      if (this.sala != null)
         if (this.sala.contains(oldSala))
            this.sala.remove(oldSala);
   }
   
   public void removeAllSala() {
      if (sala != null)
         sala.clear();
   }

}