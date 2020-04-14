package model;
import java.util.*;

/** Lekar ima recnik pregleda, kljuc je datum, vrednost pregled
 * 
 *  */
public class Lekar extends Korisnik {
   private Double prosecnaOcena;
   private int brojOcena;
   private Date rvPocetak;
   private Date rvKraj;
   
   public TipPregleda tipPregleda;
   public java.util.Collection<Operacija> operacija;
   public java.util.Collection<Odsustvo> odsustvo;
   public Pregled[] pregled;
   
   
   public java.util.Collection<Operacija> getOperacija() {
      if (operacija == null)
         operacija = new java.util.HashSet<Operacija>();
      return operacija;
   }
   
   public java.util.Iterator getIteratorOperacija() {
      if (operacija == null)
         operacija = new java.util.HashSet<Operacija>();
      return operacija.iterator();
   }
   
   public void setOperacija(java.util.Collection<Operacija> newOperacija) {
      removeAllOperacija();
      for (java.util.Iterator iter = newOperacija.iterator(); iter.hasNext();)
         addOperacija((Operacija)iter.next());
   }
   
   public void addOperacija(Operacija newOperacija) {
      if (newOperacija == null)
         return;
      if (this.operacija == null)
         this.operacija = new java.util.HashSet<Operacija>();
      if (!this.operacija.contains(newOperacija))
         this.operacija.add(newOperacija);
   }
   
   public void removeOperacija(Operacija oldOperacija) {
      if (oldOperacija == null)
         return;
      if (this.operacija != null)
         if (this.operacija.contains(oldOperacija))
            this.operacija.remove(oldOperacija);
   }
   
   public void removeAllOperacija() {
      if (operacija != null)
         operacija.clear();
   }
   public java.util.Collection<Odsustvo> getOdsustvo() {
      if (odsustvo == null)
         odsustvo = new java.util.HashSet<Odsustvo>();
      return odsustvo;
   }
   
   public java.util.Iterator getIteratorOdsustvo() {
      if (odsustvo == null)
         odsustvo = new java.util.HashSet<Odsustvo>();
      return odsustvo.iterator();
   }
   
   public void setOdsustvo(java.util.Collection<Odsustvo> newOdsustvo) {
      removeAllOdsustvo();
      for (java.util.Iterator iter = newOdsustvo.iterator(); iter.hasNext();)
         addOdsustvo((Odsustvo)iter.next());
   }
   
   public void addOdsustvo(Odsustvo newOdsustvo) {
      if (newOdsustvo == null)
         return;
      if (this.odsustvo == null)
         this.odsustvo = new java.util.HashSet<Odsustvo>();
      if (!this.odsustvo.contains(newOdsustvo))
         this.odsustvo.add(newOdsustvo);
   }
   
   public void removeOdsustvo(Odsustvo oldOdsustvo) {
      if (oldOdsustvo == null)
         return;
      if (this.odsustvo != null)
         if (this.odsustvo.contains(oldOdsustvo))
            this.odsustvo.remove(oldOdsustvo);
   }
   
   public void removeAllOdsustvo() {
      if (odsustvo != null)
         odsustvo.clear();
   }

}