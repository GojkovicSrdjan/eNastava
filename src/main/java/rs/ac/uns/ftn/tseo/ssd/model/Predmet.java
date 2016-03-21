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
	
	// -> Predaje.predmet
	@OneToMany(mappedBy="predmet", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Predaje> predavanja = new HashSet<Predaje>();
	
	// -> Pohadja.predmet
	@OneToMany(mappedBy="predmet", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Pohadja> pohadjanja = new HashSet<Pohadja>();
	
	
	
	public Predmet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Predmet(Integer predmetID, String naziv, String opis, Set<Predaje> predavanja, Set<Pohadja> pohadjanja) {
		super();
		this.predmetID = predmetID;
		this.naziv = naziv;
		this.opis = opis;
		this.predavanja = predavanja;
		this.pohadjanja = pohadjanja;
	}



	public Integer getPredmetID() {
		return predmetID;
	}

	public void setPredmetID(Integer predmetID) {
		this.predmetID = predmetID;
	}

	public Set<Predaje> getPredavanja() {
		return predavanja;
	}

	public void setPredavanja(Set<Predaje> predavanja) {
		this.predavanja = predavanja;
	}

	public Set<Pohadja> getPohadjanja() {
		return pohadjanja;
	}

	public void setPohadjanja(Set<Pohadja> pohadjanja) {
		this.pohadjanja = pohadjanja;
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


	
}
