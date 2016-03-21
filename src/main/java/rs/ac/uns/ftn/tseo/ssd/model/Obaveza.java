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
public class Obaveza {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer obavezaID;
	
	// -> Pohadja.obaveze
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Pohadja pohadja;
	
	private Date datum;
	private String tipObaveze;
	private Boolean polozeno;
	private Integer brojBodova;
	private Integer ocena;
	
	
	
	public Obaveza() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Obaveza(Integer obavezaID, Pohadja pohadja, Date datum, String tipObaveze, Boolean polozeno,
			Integer brojBodova, Integer ocena) {
		super();
		this.obavezaID = obavezaID;
		this.pohadja = pohadja;
		this.datum = datum;
		this.tipObaveze = tipObaveze;
		this.polozeno = polozeno;
		this.brojBodova = brojBodova;
		this.ocena = ocena;
	}

	
	
	public Integer getObavezaID() {
		return obavezaID;
	}

	public void setObavezaID(Integer obavezaID) {
		this.obavezaID = obavezaID;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getTipObaveze() {
		return tipObaveze;
	}

	public void setTipObaveze(String tipObaveze) {
		this.tipObaveze = tipObaveze;
	}

	public Boolean getPolozeno() {
		return polozeno;
	}

	public void setPolozeno(Boolean polozeno) {
		this.polozeno = polozeno;
	}

	public Pohadja getPohadja() {
		return pohadja;
	}

	public void setPohadja(Pohadja pohadja) {
		this.pohadja = pohadja;
	}

	public Integer getBrojBodova() {
		return brojBodova;
	}

	public void setBrojBodova(Integer brojBodova) {
		this.brojBodova = brojBodova;
	}

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}
	
}
