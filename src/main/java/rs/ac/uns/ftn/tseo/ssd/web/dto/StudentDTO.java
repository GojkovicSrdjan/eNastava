package rs.ac.uns.ftn.tseo.ssd.web.dto;

import rs.ac.uns.ftn.tseo.ssd.model.Student;

public class StudentDTO {
	
	private Integer studentID;
	private String brojIndexa;
	private KorisnikDTO korisnik;
	private ERacunDTO eRacun;
	
	public StudentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentDTO(Student student) {
		studentID = student.getStudentID();
		brojIndexa = student.getBrojIndexa();
		korisnik = new KorisnikDTO(student.getKorisnik());
		eRacun = new ERacunDTO(student.geteRacun());
	}

	public StudentDTO(Integer studentID, String brojIndexa, KorisnikDTO korisnik, ERacunDTO eRacun) {
		super();
		this.studentID = studentID;
		this.brojIndexa = brojIndexa;
		this.korisnik = korisnik;
		this.eRacun = eRacun;
	}

	
	
	public Integer getStudentID() {
		return studentID;
	}
	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}
	public String getBrojIndexa() {
		return brojIndexa;
	}
	public void setBrojIndexa(String brojIndexa) {
		this.brojIndexa = brojIndexa;
	}
	public KorisnikDTO getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(KorisnikDTO korisnik) {
		this.korisnik = korisnik;
	}
	public ERacunDTO geteRacun() {
		return eRacun;
	}
	public void seteRacun(ERacunDTO eRacun) {
		this.eRacun = eRacun;
	}
	
}
