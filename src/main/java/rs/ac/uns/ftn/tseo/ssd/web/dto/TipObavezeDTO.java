package rs.ac.uns.ftn.tseo.ssd.web.dto;

import java.util.Date;

import rs.ac.uns.ftn.tseo.ssd.model.TipObaveze;

public class TipObavezeDTO {

	
	private Integer tipObavezeID;
	private Date datum;
	private String naziv;
	private String opis;
	
	private PredmetDTO predmet;

	
	
	public TipObavezeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipObavezeDTO(TipObaveze tipObaveze) {
		tipObavezeID = tipObaveze.getTipObavezeID();
		datum = tipObaveze.getDatum();
		naziv = tipObaveze.getNaziv();
		opis = tipObaveze.getOpis();
		predmet = new PredmetDTO(tipObaveze.getPredmet());
	}
	
	public TipObavezeDTO(Integer tipObavezeID, Date datum, String naziv, String opis, PredmetDTO predmet) {
		super();
		this.tipObavezeID = tipObavezeID;
		this.datum = datum;
		this.naziv = naziv;
		this.opis = opis;
		this.predmet = predmet;
	}

	
	
	public Integer getTipObavezeID() {
		return tipObavezeID;
	}
	public void setTipObavezeID(Integer tipObavezeID) {
		this.tipObavezeID = tipObavezeID;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
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
	public PredmetDTO getPredmet() {
		return predmet;
	}
	public void setPredmet(PredmetDTO predmet) {
		this.predmet = predmet;
	}
	
}
