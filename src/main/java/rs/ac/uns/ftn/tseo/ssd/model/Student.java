package rs.ac.uns.ftn.tseo.ssd.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.HashSet;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String brojIndexa;
	
	// -> Korisnik.JMBG
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Integer JMBG;
	
	// -> ERacun.eRacunID
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Integer eRacunID;
	
	// -> Dokument.dokumentID
	@OneToMany(mappedBy = "documentID", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Dokument> dokumenta = new HashSet<Dokument>();
	
	// -> Pohadja.brojIndexa
	@OneToMany(mappedBy = "brojIndexa", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Predmet> predmeti = new HashSet<Predmet>();

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String brojIndexa, Integer jMBG, Integer eRacunID, Set<Dokument> dokumenta, Set<Predmet> predmeti) {
		super();
		this.brojIndexa = brojIndexa;
		JMBG = jMBG;
		this.eRacunID = eRacunID;
		this.dokumenta = dokumenta;
		this.predmeti = predmeti;
	}

	public String getBrojIndexa() {
		return brojIndexa;
	}

	public void setBrojIndexa(String brojIndexa) {
		this.brojIndexa = brojIndexa;
	}

	public Integer getJMBG() {
		return JMBG;
	}

	public void setJMBG(Integer jMBG) {
		JMBG = jMBG;
	}

	public Integer geteRacunID() {
		return eRacunID;
	}

	public void seteRacunID(Integer eRacunID) {
		this.eRacunID = eRacunID;
	}

	public Set<Dokument> getDokumenta() {
		return dokumenta;
	}

	public void setDokumenta(Set<Dokument> dokumenta) {
		this.dokumenta = dokumenta;
	}

	public Set<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(Set<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
}
