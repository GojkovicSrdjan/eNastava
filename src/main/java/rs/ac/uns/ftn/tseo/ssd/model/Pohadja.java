package rs.ac.uns.ftn.tseo.ssd.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pohadja {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pohadjaID;
	
	// -> Student.predmeti
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Integer brojIndexa;
	
	// -> Predmet.studenti
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Integer predmetID;
	
	// -> Obaveza.pohadjaID
	@OneToMany(mappedBy="pohadjaID",cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Obaveza> obaveze = new HashSet<Obaveza>();

	public Pohadja() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pohadja(Integer pohadjaID, Integer brojIndexa, Integer predmetID, Set<Obaveza> obaveze) {
		super();
		this.pohadjaID = pohadjaID;
		this.brojIndexa = brojIndexa;
		this.predmetID = predmetID;
		this.obaveze = obaveze;
	}

	public Integer getPohadjaID() {
		return pohadjaID;
	}

	public void setPohadjaID(Integer pohadjaID) {
		this.pohadjaID = pohadjaID;
	}

	public Integer getBrojIndexa() {
		return brojIndexa;
	}

	public void setBrojIndexa(Integer brojIndexa) {
		this.brojIndexa = brojIndexa;
	}

	public Integer getPredmetID() {
		return predmetID;
	}

	public void setPredmetID(Integer predmetID) {
		this.predmetID = predmetID;
	}

	public Set<Obaveza> getObaveze() {
		return obaveze;
	}

	public void setObaveze(Set<Obaveza> obaveze) {
		this.obaveze = obaveze;
	}
	
}
