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
	
	// -> Student.pohadjanja
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Student student;
	
	// -> Predmet.pohadjanja
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Predmet predmet;
	
	// -> Obaveza.pohadja
	@OneToMany(mappedBy="pohadja",cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Obaveza> obaveze = new HashSet<Obaveza>();
	
	
	
	public Pohadja() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pohadja(Integer pohadjaID, Student student, Predmet predmet, Set<Obaveza> obaveze) {
		super();
		this.pohadjaID = pohadjaID;
		this.student = student;
		this.predmet = predmet;
		this.obaveze = obaveze;
	}
	


	public Integer getPohadjaID() {
		return pohadjaID;
	}

	public void setPohadjaID(Integer pohadjaID) {
		this.pohadjaID = pohadjaID;
	}

	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public Predmet getPredmet() {
		return predmet;
	}


	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}


	public Set<Obaveza> getObaveze() {
		return obaveze;
	}

	public void setObaveze(Set<Obaveza> obaveze) {
		this.obaveze = obaveze;
	}
	
}
