package rs.ac.uns.ftn.tseo.ssd.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Obaveza {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer obavezaID;
	
	// -> TipObaveze.obaveze
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private TipObaveze tipObaveze;
	
	// -> Student.obaveze
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Student student;
	
	private Boolean polozeno;
	private Integer brojBodova;
	private Integer ocena;
	
	
	
	public Obaveza() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Obaveza(Integer obavezaID, TipObaveze tipObaveze, Student student, Boolean polozeno, Integer brojBodova,
			Integer ocena) {
		super();
		this.obavezaID = obavezaID;
		this.tipObaveze = tipObaveze;
		this.student = student;
		this.polozeno = polozeno;
		this.brojBodova = brojBodova;
		this.ocena = ocena;
	}
	
	
	
	public Integer getObavezaID() {
		return obavezaID;
	}

	public void setObavezaID(Integer obavezaID) {
		this.obavezaID = obavezaID;
	}

	public TipObaveze getTipObaveze() {
		return tipObaveze;
	}

	public void setTipObaveze(TipObaveze tipObaveze) {
		this.tipObaveze = tipObaveze;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Boolean getPolozeno() {
		return polozeno;
	}

	public void setPolozeno(Boolean polozeno) {
		this.polozeno = polozeno;
	}

	public Integer getBrojBodova() {
		return brojBodova;
	}

	public void setBrojBodova(Integer brojBodova) {
		this.brojBodova = brojBodova;
	}

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}
	
}
