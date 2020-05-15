package main.mrs.dto;


import main.mrs.model.Recept;

public class IzvestajDTO {
	private Integer id;
	private String opis;   
	public DijagnozaDTO dijagnoza;
	public Recept recept;
   
	public IzvestajDTO() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	

	public void setId(Integer id) {
		this.id = id;
	}
   
   
   public DijagnozaDTO getDijagnoza() {
		return dijagnoza;
	}

	public void setDijagnoza(DijagnozaDTO dijagnoza) {
		this.dijagnoza = dijagnoza;
	}

	public Recept getRecept() {
		return recept;
	}

	public void setRecept(Recept recept) {
		this.recept = recept;
	}

public String getOpis() {
	return opis;
}


	public void setOpis(String opis) {
		this.opis = opis;
	}

}