package rs.ac.uns.ftn.tseo.ssd.web.dto;

import rs.ac.uns.ftn.tseo.ssd.model.Korisnik;

public class KorisnikDTO {
	
	private Integer korisnikID;
	private Integer JMBG;
	private String korisnickoIme;
	private String lozinka;
	
	private String ime;
	private String prezime;
	private String brojTelefona;
	private String email;
	
	private String ulicaIBroj;
	private Integer postanskiBroj;
	private String mesto;
	
	
	
	public KorisnikDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public KorisnikDTO(Korisnik korisnik){
		this(
			korisnik.getKorisnikID(),
			korisnik.getJMBG(),
			korisnik.getKorisnickoIme(),
			korisnik.getLozinka(),
			korisnik.getIme(),
			korisnik.getPrezime(),
			korisnik.getBrojTelefona(),
			korisnik.getEmail(),
			korisnik.getUlicaIBroj(),
			korisnik.getPostanskiBroj(),
			korisnik.getMesto()
			);
	}
	
	public KorisnikDTO(Integer korisnikID, Integer jMBG, String korisnickoIme, String lozinka, String ime,
			String prezime, String brojTelefona, String email, String ulicaIBroj, Integer postanskiBroj, String mesto) {
		super();
		this.korisnikID = korisnikID;
		JMBG = jMBG;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.brojTelefona = brojTelefona;
		this.email = email;
		this.ulicaIBroj = ulicaIBroj;
		this.postanskiBroj = postanskiBroj;
		this.mesto = mesto;
	}
	
	
	
	public Integer getKorisnikID() {
		return korisnikID;
	}
	public void setKorisnikID(Integer korisnikID) {
		this.korisnikID = korisnikID;
	}
	public Integer getJMBG() {
		return JMBG;
	}
	public void setJMBG(Integer jMBG) {
		JMBG = jMBG;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUlicaIBroj() {
		return ulicaIBroj;
	}
	public void setUlicaIBroj(String ulicaIBroj) {
		this.ulicaIBroj = ulicaIBroj;
	}
	public Integer getPostanskiBroj() {
		return postanskiBroj;
	}
	public void setPostanskiBroj(Integer postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	
	
}
