package rs.ac.uns.ftn.tseo.ssd.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Uplata {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer uplataID;
	
	// -> ERacun.eRacunID
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private ERacun eRacun;
	
	private Date datum;
	private String svrha;
	private Double iznos;
	
	public Uplata() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Uplata(Integer uplataID, ERacun eRacun, Date datum, String svrha, Double iznos) {
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


	public ERacun geteRacun() {
		return eRacun;
	}


	public void seteRacun(ERacun eRacun) {
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
