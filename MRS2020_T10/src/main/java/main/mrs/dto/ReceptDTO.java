package main.mrs.dto;
import java.util.*;

import main.mrs.model.Lek;
import main.mrs.model.MedSestra;
import main.mrs.model.Recept;

public class ReceptDTO {
	private Integer id;
   public MedSestraDTO medSestra;
   public Set<LekDTO> lek;
   public ReceptDTO() {
		
	}
   public ReceptDTO(Recept r) {
		this(r.getId(), r.getLek());
	}
	
	public ReceptDTO(Integer id2, Set<Lek> lek2) {
		this.id = id2;
		this.lek = konvertuj(lek2);
		this.medSestra = null;//OdsustvoDTO.setujSestra(medSestra2);
	}
	
	private Set<LekDTO> konvertuj(Set<Lek> lek2) {
		Set<LekDTO> novi = new HashSet<LekDTO>();
		for(Lek l: lek2) {
			LekDTO ld = new LekDTO();
			ld.setNaziv(l.getNaziv());
			ld.setId(l.getId());
			ld.setSifra(l.getSifra());
			novi.add(ld);
		}
		return novi;
	}

	public MedSestraDTO getMedSestra() {
		return medSestra;
	}

	public void setMedSestra(MedSestraDTO medSestra) {
		this.medSestra = medSestra;
	}

	public Integer getId() {
			return id;
		}

	public void setId(Integer id) {
		this.id = id;
	}
   public Set<LekDTO> getLek() {
      if (lek == null)
         lek = new java.util.HashSet<LekDTO>();
      return lek;
   }
   
   public void setLek(Set<LekDTO> newLek) {
      this.lek = newLek;
   }
   
   public void addLek(LekDTO newLek) {
      if (newLek == null)
         return;
      if (this.lek == null)
         this.lek = new java.util.HashSet<LekDTO>();
      if (!this.lek.contains(newLek))
         this.lek.add(newLek);
   }
   
   public void removeLek(LekDTO oldLek) {
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

}