package main.mrs.model;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class AdminKlinike extends Korisnik {
   
	
	
	@OneToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="odsustvo_id", nullable=false)
    public Set<Odsustvo> odsustvo;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    public Klinika klinika;
   
   
   public Set<Odsustvo> getOdsustvo() {
      if (odsustvo == null)
         odsustvo = new HashSet<Odsustvo>();
      return odsustvo;
   }
   
   public void setOdsustvo(Set<Odsustvo> newOdsustvo) {
      this.odsustvo = newOdsustvo;
   }
   
   public void addOdsustvo(Odsustvo newOdsustvo) {
      if (newOdsustvo == null)
         return;
      if (this.odsustvo == null)
         this.odsustvo = new HashSet<Odsustvo>();
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
   public Klinika getKlinika() {
      return klinika;
   }
   
   
}