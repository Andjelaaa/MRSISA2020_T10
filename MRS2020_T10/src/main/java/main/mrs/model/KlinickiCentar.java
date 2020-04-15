package main.mrs.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class KlinickiCentar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="kc_id", nullable=false)
    public Set<Klinika> klinika;
   
   
   public Set<Klinika> getKlinika() {
      if (klinika == null)
         klinika = new HashSet<Klinika>();
      return klinika;
   }

   public void setKlinika(Set<Klinika> newKlinika) {
      this.klinika = newKlinika;
   }
   
   public void addKlinika(Klinika newKlinika) {
      if (newKlinika == null)
         return;
      if (this.klinika == null)
         this.klinika = new HashSet<Klinika>();
      if (!this.klinika.contains(newKlinika))
         this.klinika.add(newKlinika);
   }
   
   public void removeKlinika(Klinika oldKlinika) {
      if (oldKlinika == null)
         return;
      if (this.klinika != null)
         if (this.klinika.contains(oldKlinika))
            this.klinika.remove(oldKlinika);
   }
   
   public void removeAllKlinika() {
      if (klinika != null)
         klinika.clear();
   }

}