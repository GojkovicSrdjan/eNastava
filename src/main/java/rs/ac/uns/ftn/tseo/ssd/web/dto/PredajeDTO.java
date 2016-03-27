package rs.ac.uns.ftn.tseo.ssd.web.dto;

import rs.ac.uns.ftn.tseo.ssd.model.Predaje;

public class PredajeDTO {

	private Integer predajeID;
	
	private PredmetDTO predmet;
	private ProfesorDTO profesor;
	
	private String tipPredavanja; // Predavanja, Vezbe, ...
	private String uloga; // Uloga profesora (Profesor, Asistent...)
	
	
	
	public PredajeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PredajeDTO(Predaje predaje){
		predajeID = predaje.getPredajeID();
		predmet = new PredmetDTO(predaje.getPredmet());
		profesor = new ProfesorDTO(predaje.getProfesor());
		tipPredavanja = predaje.getTipPredavanja();
		uloga = predaje.getUloga();
	}
	
	public PredajeDTO(Integer predajeID, PredmetDTO predmet, ProfesorDTO profesor, String tipPredavanja, String uloga) {
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
	public PredmetDTO getPredmet() {
		return predmet;
	}
	public void setPredmet(PredmetDTO predmet) {
		this.predmet = predmet;
	}
	public ProfesorDTO getProfesor() {
		return profesor;
	}
	public void setProfesor(ProfesorDTO profesor) {
		this.profesor = profesor;
	}
	public String getTipPredavanja() {
		return tipPredavanja;
	}
	public void setTipPredavanja(String tipPredavanja) {
		this.tipPredavanja = tipPredavanja;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
	
}
