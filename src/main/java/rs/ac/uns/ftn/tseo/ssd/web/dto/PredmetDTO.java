package rs.ac.uns.ftn.tseo.ssd.web.dto;

import rs.ac.uns.ftn.tseo.ssd.model.Predmet;

public class PredmetDTO {

	
	private Integer predmetID;
	private String naziv;
	private String opis;
	
	
	
	public PredmetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PredmetDTO(Predmet predmet) {
		this(
			predmet.getPredmetID(),
			predmet.getNaziv(),
			predmet.getOpis()
			);
	}
	
	public PredmetDTO(Integer predmetID, String naziv, String opis) {
		super();
		this.predmetID = predmetID;
		this.naziv = naziv;
		this.opis = opis;
	}
	
	
	
	public Integer getPredmetID() {
		return predmetID;
	}
	public void setPredmetID(Integer predmetID) {
		this.predmetID = predmetID;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	
}
