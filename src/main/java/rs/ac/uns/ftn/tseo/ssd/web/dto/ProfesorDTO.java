package rs.ac.uns.ftn.tseo.ssd.web.dto;

import rs.ac.uns.ftn.tseo.ssd.model.Profesor;

public class ProfesorDTO {

	private Integer profesorID;
	private KorisnikDTO korisnik;
	private String zvanje;
	
	
	
	public ProfesorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProfesorDTO(Profesor profesor) {
		profesorID = profesor.getProfesorID();
		korisnik = new KorisnikDTO(profesor.getKorisnik());
		zvanje = profesor.getZvanje();
	}
	
	public ProfesorDTO(Integer profesorID, KorisnikDTO korisnik, String zvanje) {
		super();
		this.profesorID = profesorID;
		this.korisnik = korisnik;
		this.zvanje = zvanje;
	}
	
	
	
	public Integer getProfesorID() {
		return profesorID;
	}
	public void setProfesorID(Integer profesorID) {
		this.profesorID = profesorID;
	}
	public KorisnikDTO getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(KorisnikDTO korisnik) {
		this.korisnik = korisnik;
	}
	public String getZvanje() {
		return zvanje;
	}
	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}
	
}
