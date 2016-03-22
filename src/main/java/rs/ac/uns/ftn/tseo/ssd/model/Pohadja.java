package rs.ac.uns.ftn.tseo.ssd.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	
	
	
	public Pohadja() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pohadja(Integer pohadjaID, Student student, Predmet predmet) {
		super();
		this.pohadjaID = pohadjaID;
		this.student = student;
		this.predmet = predmet;
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


}
