package rs.ac.uns.ftn.tseo.ssd.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Profesor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer profesorID;
	
	// -> Korisnik.JMBG
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Integer JMBG;
	
	private String zvanje;
	
	// -> Predaje.profesorID
	@OneToMany(mappedBy="profesorID", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Predmet> predmeti = new HashSet<Predmet>();

	public Profesor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Profesor(Integer profesorID, Integer jMBG, String zvanje, Set<Predmet> predmeti) {
		super();
		this.profesorID = profesorID;
		JMBG = jMBG;
		this.zvanje = zvanje;
		this.predmeti = predmeti;
	}

	public Integer getProfesorID() {
		return profesorID;
	}

	public void setProfesorID(Integer profesorID) {
		this.profesorID = profesorID;
	}

	public Integer getJMBG() {
		return JMBG;
	}

	public void setJMBG(Integer jMBG) {
		JMBG = jMBG;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public Set<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(Set<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
}
