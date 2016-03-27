package rs.ac.uns.ftn.tseo.ssd.web.dto;

import rs.ac.uns.ftn.tseo.ssd.model.Administrator;

public class AdministratorDTO {

	private Integer adminID;
	private KorisnikDTO korisnik;
	
	
	
	public AdministratorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdministratorDTO(Administrator administrator){
		adminID = administrator.getAdminID();
		korisnik = new KorisnikDTO(administrator.getKorisnik());
	}

	public AdministratorDTO(Integer adminID, KorisnikDTO korisnik) {
		super();
		this.adminID = adminID;
		this.korisnik = korisnik;
	}
	
	

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}

	public KorisnikDTO getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KorisnikDTO korisnik) {
		this.korisnik = korisnik;
	}
	
	
	
}
