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
public class ERacun {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer eRacunID;
	
	private Double stanjeNaERacunu;
	
	// -> Uplata.eRacunID
	@OneToMany(mappedBy = "eRacunID", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Uplata> uplate = new HashSet<Uplata>();

	public ERacun() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ERacun(Integer eRacunID, Double stanjeNaERacunu, Set<Uplata> uplate) {
		super();
		this.eRacunID = eRacunID;
		this.stanjeNaERacunu = stanjeNaERacunu;
		this.uplate = uplate;
	}

	public Integer geteRacunID() {
		return eRacunID;
	}

	public void seteRacunID(Integer eRacunID) {
		this.eRacunID = eRacunID;
	}

	public Double getStanjeNaERacunu() {
		return stanjeNaERacunu;
	}

	public void setStanjeNaERacunu(Double stanjeNaERacunu) {
		this.stanjeNaERacunu = stanjeNaERacunu;
	}

	public Set<Uplata> getUplate() {
		return uplate;
	}

	public void setUplate(Set<Uplata> uplate) {
		this.uplate = uplate;
	}
	
}
