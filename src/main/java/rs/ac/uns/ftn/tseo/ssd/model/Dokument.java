package rs.ac.uns.ftn.tseo.ssd.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Dokument {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer dokumentID;
	
	// -> Student.dokumenta
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Integer brojIndexa;
	
	private String naziv;
	private String tip;
	private String putanjaDoDokumenta;
	
	public Dokument() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dokument(Integer dokumentID, Integer brojIndexa, String naziv, String tip, String putanjaDoDokumenta) {
		super();
		this.dokumentID = dokumentID;
		this.brojIndexa = brojIndexa;
		this.naziv = naziv;
		this.tip = tip;
		this.putanjaDoDokumenta = putanjaDoDokumenta;
	}

	public Integer getDokumentID() {
		return dokumentID;
	}

	public void setDokumentID(Integer dokumentID) {
		this.dokumentID = dokumentID;
	}

	public Integer getBrojIndexa() {
		return brojIndexa;
	}

	public void setBrojIndexa(Integer brojIndexa) {
		this.brojIndexa = brojIndexa;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getPutanjaDoDokumenta() {
		return putanjaDoDokumenta;
	}

	public void setPutanjaDoDokumenta(String putanjaDoDokumenta) {
		this.putanjaDoDokumenta = putanjaDoDokumenta;
	}

}
