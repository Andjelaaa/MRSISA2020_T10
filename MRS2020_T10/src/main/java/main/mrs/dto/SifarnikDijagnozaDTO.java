package main.mrs.dto;
import java.util.*;

public class SifarnikDijagnozaDTO {
	
   private Integer id;
   public Set<DijagnozaDTO> dijagnoza;
   
   public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
   public Set<DijagnozaDTO> getDijagnoza() {
      if (dijagnoza == null)
         dijagnoza = new java.util.HashSet<DijagnozaDTO>();
      return dijagnoza;
   }
   
   public void setDijagnoza(Set<DijagnozaDTO> newDijagnoza) {
      this.dijagnoza = newDijagnoza;
   }
   
   public void addDijagnoza(DijagnozaDTO newDijagnoza) {
      if (newDijagnoza == null)
         return;
      if (this.dijagnoza == null)
         this.dijagnoza = new java.util.HashSet<DijagnozaDTO>();
      if (!this.dijagnoza.contains(newDijagnoza))
         this.dijagnoza.add(newDijagnoza);
   }
   
   public void removeDijagnoza(DijagnozaDTO oldDijagnoza) {
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