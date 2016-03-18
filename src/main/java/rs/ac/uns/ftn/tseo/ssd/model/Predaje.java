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
	
	// -> Predmet.profesori
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Integer predmetID;
	
	// -> Profesor.predmeti
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Integer profesorID;
	
	private String tipPredavanja; // Predavanja, Vezbe, ...

	public Predaje() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Predaje(Integer predajeID, Integer predmetID, Integer profesorID, String tipPredavanja) {
		super();
		this.predajeID = predajeID;
		this.predmetID = predmetID;
		this.profesorID = profesorID;
		this.tipPredavanja = tipPredavanja;
	}

	public Integer getPredajeID() {
		return predajeID;
	}

	public void setPredajeID(Integer predajeID) {
		this.predajeID = predajeID;
	}

	public Integer getPredmetID() {
		return predmetID;
	}

	public void setPredmetID(Integer predmetID) {
		this.predmetID = predmetID;
	}

	public Integer getProfesorID() {
		return profesorID;
	}

	public void setProfesorID(Integer profesorID) {
		this.profesorID = profesorID;
	}

	public String getTipPredavanja() {
		return tipPredavanja;
	}

	public void setTipPredavanja(String tipPredavanja) {
		this.tipPredavanja = tipPredavanja;
	}
	
}
