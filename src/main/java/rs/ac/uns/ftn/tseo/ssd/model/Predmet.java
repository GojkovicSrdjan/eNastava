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

@Entity
public class Predmet {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer predmetID;
	
	private String naziv;
	
	private String opis;
	
	// -> Predaje.predmetID
	@OneToMany(mappedBy="predmetID", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Profesor> profesori = new HashSet<Profesor>();
	
	// -> Pohadja.predmetID
	@OneToMany(mappedBy="predmetID", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Student> studenti = new HashSet<Student>();

	public Predmet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Predmet(Integer predmetID, String naziv, String opis, Set<Profesor> profesori, Set<Student> studenti) {
		super();
		this.predmetID = predmetID;
		this.naziv = naziv;
		this.opis = opis;
		this.profesori = profesori;
		this.studenti = studenti;
	}

	public Integer getPredmetID() {
		return predmetID;
	}

	public void setPredmetID(Integer predmetID) {
		this.predmetID = predmetID;
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

	public Set<Profesor> getProfesori() {
		return profesori;
	}

	public void setProfesori(Set<Profesor> profesori) {
		this.profesori = profesori;
	}

	public Set<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(Set<Student> studenti) {
		this.studenti = studenti;
	}
	
}
