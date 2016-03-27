package rs.ac.uns.ftn.tseo.ssd.web.dto;

import rs.ac.uns.ftn.tseo.ssd.model.ERacun;

public class ERacunDTO {

	private Integer eRacunID;
	private Double stanjeNaERacunu;
	
	
	
	public ERacunDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ERacunDTO(ERacun eRacun){
		eRacunID = eRacun.geteRacunID();
		stanjeNaERacunu = eRacun.getStanjeNaERacunu();
	}
	
	public ERacunDTO(Integer eRacunID, Double stanjeNaERacunu) {
		super();
		this.eRacunID = eRacunID;
		this.stanjeNaERacunu = stanjeNaERacunu;
	}
	
	
	
	public Integer geteRacunID() {
		return eRacunID;
	}
	public void seteRacunID(Integer eRacunID) {
		this.eRacunID = eRacunID;
	}
	public Double getStanjeNaERacunu() {
		return stanjeNaERacunu;
	}
	public void setStanjeNaERacunu(Double stanjeNaERacunu) {
		this.stanjeNaERacunu = stanjeNaERacunu;
	}
	
	
}
