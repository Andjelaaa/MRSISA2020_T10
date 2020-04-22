package main.mrs.dto;
import java.util.*;

public class RadniKalendarDTO {
	private Integer id;
   public Set<OdsustvoDTO> odsustvo;
   
   public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
   public Set<OdsustvoDTO> getOdsustvo() {
      if (odsustvo == null)
         odsustvo = new java.util.HashSet<OdsustvoDTO>();
      return odsustvo;
   }

   public void setOdsustvo(Set<OdsustvoDTO> newOdsustvo) {
      this.odsustvo = newOdsustvo;
   }
   
   public void addOdsustvo(OdsustvoDTO newOdsustvo) {
      if (newOdsustvo == null)
         return;
      if (this.odsustvo == null)
         this.odsustvo = new java.util.HashSet<OdsustvoDTO>();
      if (!this.odsustvo.contains(newOdsustvo))
         this.odsustvo.add(newOdsustvo);
   }
   
   public void removeOdsustvo(OdsustvoDTO oldOdsustvo) {
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