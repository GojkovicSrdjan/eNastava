package rs.ac.uns.ftn.tseo.ssd.web.dto;

import java.util.Date;

import rs.ac.uns.ftn.tseo.ssd.model.Uplata;

public class UplataDTO {

	private Integer uplataID;
	
	private ERacunDTO eRacun;
	
	private Date datum;
	private String svrha;
	private Double iznos;
	
	
	
	public UplataDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UplataDTO(Uplata uplata){
		uplataID = uplata.getUplataID();
		eRacun = new ERacunDTO(uplata.geteRacun());
		datum = uplata.getDatum();
		svrha = uplata.getSvrha();
		iznos = uplata.getIznos();
	}
	
	public UplataDTO(Integer uplataID, ERacunDTO eRacun, Date datum, String svrha, Double iznos) {
		super();
		this.uplataID = uplataID;
		this.eRacun = eRacun;
		this.datum = datum;
		this.svrha = svrha;
		this.iznos = iznos;
	}
	
	
	
	public Integer getUplataID() {
		return uplataID;
	}
	public void setUplataID(Integer uplataID) {
		this.uplataID = uplataID;
	}
	public ERacunDTO geteRacun() {
		return eRacun;
	}
	public void seteRacun(ERacunDTO eRacun) {
		this.eRacun = eRacun;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public String getSvrha() {
		return svrha;
	}
	public void setSvrha(String svrha) {
		this.svrha = svrha;
	}
	public Double getIznos() {
		return iznos;
	}
	public void setIznos(Double iznos) {
		this.iznos = iznos;
	}
	
	
}
