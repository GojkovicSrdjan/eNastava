package rs.ac.uns.ftn.tseo.ssd.model;

import java.util.Date;
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
public class TipObaveze {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tipObavezeID;
	
	private Date datum;
	private String naziv;
	private String opis;
	
	// -> Predmet.tipoviObaveza
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Predmet predmet;

	// -> Obaveza.tipObaveze
	@OneToMany(mappedBy="tipObaveze",cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Set<Obaveza> obaveze = new HashSet<Obaveza>();

	
	
	public TipObaveze() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipObaveze(Integer tipObavezeID, Date datum, String naziv, String opis, Predmet predmet,
			Set<Obaveza> obaveze) {
		super();
		this.tipObavezeID = tipObavezeID;
		this.datum = datum;
		this.naziv = naziv;
		this.opis = opis;
		this.predmet = predmet;
		this.obaveze = obaveze;
	}
	
	
	
	public Integer getTipObavezeID() {
		return tipObavezeID;
	}

	public void setTipObavezeID(Integer tipObavezeID) {
		this.tipObavezeID = tipObavezeID;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
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
