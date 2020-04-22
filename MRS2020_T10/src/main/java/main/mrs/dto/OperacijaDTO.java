package main.mrs.dto;
import java.util.*;

public class OperacijaDTO {
	private Integer id;
   private Date datumVreme;
   private int trajanje;
   private StatusDTO status;
   
   public StavkaCenovnikaDTO stavkaCenovnika;
   public SalaDTO sala;
   public Set<LekarDTO> lekar;
  
   public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
public StavkaCenovnikaDTO getStavkaCenovnika() {
	return stavkaCenovnika;
}

public void setStavkaCenovnika(StavkaCenovnikaDTO stavkaCenovnika) {
	this.stavkaCenovnika = stavkaCenovnika;
}

public SalaDTO getSala() {
	return sala;
}

public void setSala(SalaDTO sala) {
	this.sala = sala;
}

public Date getDatumVreme() {
	return datumVreme;
}

public void setDatumVreme(Date datumVreme) {
	this.datumVreme = datumVreme;
}

public int getTrajanje() {
	return trajanje;
}

public void setTrajanje(int trajanje) {
	this.trajanje = trajanje;
}

public StatusDTO getStatus() {
	return status;
}

public void setStatus(StatusDTO status) {
	this.status = status;
}

public Set<LekarDTO> getLekar() {
	return lekar;
}

public void setLekar(Set<LekarDTO> lekar) {
	this.lekar = lekar;
}

}