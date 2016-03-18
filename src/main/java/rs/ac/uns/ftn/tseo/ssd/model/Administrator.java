package rs.ac.uns.ftn.tseo.ssd.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Administrator {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer adminID;
	
	// Korisnik.JMBG
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Integer JMBG;

	public Administrator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Administrator(Integer adminID, Integer jMBG) {
		super();
		this.adminID = adminID;
		JMBG = jMBG;
	}

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}

	public Integer getJMBG() {
		return JMBG;
	}

	public void setJMBG(Integer jMBG) {
		JMBG = jMBG;
	}
	
}
