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
	
	// -> Korisnik
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Korisnik korisnik;
	
	private String zvanje;
	
	// -> Predaje.profesor
	@OneToMany(mappedBy="profesor", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Predaje> predavanja = new HashSet<Predaje>();
	
	
	
	public Profesor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Profesor(Integer profesorID, Korisnik korisnik, String zvanje, Set<Predaje> predavanja) {
		super();
		this.profesorID = profesorID;
		this.korisnik = korisnik;
		this.zvanje = zvanje;
		this.predavanja = predavanja;
	}
	
	
	
	public Integer getProfesorID() {
		return profesorID;
	}

	public void setProfesorID(Integer profesorID) {
		this.profesorID = profesorID;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public Set<Predaje> getPredavanja() {
		return predavanja;
	}

	public void setPredavanja(Set<Predaje> predavanja) {
		this.predavanja = predavanja;
	}

	
}
