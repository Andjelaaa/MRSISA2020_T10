package main.mrs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MedicinskaSestra")
public class MedSestra extends Korisnik {
	
   @OneToMany(mappedBy="medSestra",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  // @JoinColumn(name="medSestra", nullable=false)
   public Set<Odsustvo> odsustvo;
   
   
   public Set<Odsustvo> getOdsustvo() {
      if (odsustvo == null)
         odsustvo = new HashSet<Odsustvo>();
      return odsustvo;
   }

   
   public void setOdsustvo(HashSet<Odsustvo> newOdsustvo) {
      this.odsustvo = newOdsustvo;
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