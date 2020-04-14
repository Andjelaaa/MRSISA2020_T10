package model;
import java.util.*;

public class StavkaCenovnika {
   private double cena;
   
   public java.util.Collection<TipPregleda> tipPregleda;
   
   
   public java.util.Collection<TipPregleda> getTipPregleda() {
      if (tipPregleda == null)
         tipPregleda = new java.util.HashSet<TipPregleda>();
      return tipPregleda;
   }
   
   public java.util.Iterator getIteratorTipPregleda() {
      if (tipPregleda == null)
         tipPregleda = new java.util.HashSet<TipPregleda>();
      return tipPregleda.iterator();
   }
   
   public void setTipPregleda(java.util.Collection<TipPregleda> newTipPregleda) {
      removeAllTipPregleda();
      for (java.util.Iterator iter = newTipPregleda.iterator(); iter.hasNext();)
         addTipPregleda((TipPregleda)iter.next());
   }
   
   public void addTipPregleda(TipPregleda newTipPregleda) {
      if (newTipPregleda == null)
         return;
      if (this.tipPregleda == null)
         this.tipPregleda = new java.util.HashSet<TipPregleda>();
      if (!this.tipPregleda.contains(newTipPregleda))
         this.tipPregleda.add(newTipPregleda);
   }
   
   public void removeTipPregleda(TipPregleda oldTipPregleda) {
      if (oldTipPregleda == null)
         return;
      if (this.tipPregleda != null)
         if (this.tipPregleda.contains(oldTipPregleda))
            this.tipPregleda.remove(oldTipPregleda);
   }
   
   public void removeAllTipPregleda() {
      if (tipPregleda != null)
         tipPregleda.clear();
   }

}