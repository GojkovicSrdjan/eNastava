package rs.ac.uns.ftn.tseo.ssd.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Predaje {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer predajeID;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Predmet predmet;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Profesor profesor;
	
	private String tipPredavanja; // Predavanja, Vezbe, ...
	
	private String uloga;//Uloga profesora (Profesor, Asistent...)
	
	
	public Predaje() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Predaje(Integer predajeID, Predmet predmet, Profesor profesor, String tipPredavanja, String uloga) {
		super();
		this.predajeID = predajeID;
		this.predmet = predmet;
		this.profesor = profesor;
		this.tipPredavanja = tipPredavanja;
		this.uloga = uloga;
	}



	public Integer getPredajeID() {
		return predajeID;
	}

	public void setPredajeID(Integer predajeID) {
		this.predajeID = predajeID;
	}



	public Predmet getPredmet() {
		return predmet;
	}



	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}



	public Profesor getProfesor() {
		return profesor;
	}



	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}



	public String getUloga() {
		return uloga;
	}



	public void setUloga(String uloga) {
		this.uloga = uloga;
	}



	public String getTipPredavanja() {
		return tipPredavanja;
	}

	public void setTipPredavanja(String tipPredavanja) {
		this.tipPredavanja = tipPredavanja;
	}
	
}
