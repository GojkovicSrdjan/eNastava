package rs.ac.uns.ftn.tseo.ssd.web.dto;

import rs.ac.uns.ftn.tseo.ssd.model.Obaveza;

public class ObavezaDTO {
	
	private Integer obavezaID;
	
	private TipObavezeDTO tipObaveze;
	private StudentDTO student;
	
	private Boolean polozeno;
	private Integer brojBodova;
	private Integer ocena;
	
	
	
	public ObavezaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ObavezaDTO(Obaveza obaveza){
		obavezaID = obaveza.getObavezaID();
		tipObaveze = new TipObavezeDTO(obaveza.getTipObaveze());
		student = new StudentDTO(obaveza.getStudent());
		polozeno = obaveza.getPolozeno();
		brojBodova = obaveza.getBrojBodova();
		ocena = obaveza.getOcena();
	}
	
	public ObavezaDTO(Integer obavezaID, TipObavezeDTO tipObaveze, StudentDTO student, Boolean polozeno,
			Integer brojBodova, Integer ocena) {
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
	public TipObavezeDTO getTipObaveze() {
		return tipObaveze;
	}
	public void setTipObaveze(TipObavezeDTO tipObaveze) {
		this.tipObaveze = tipObaveze;
	}
	public StudentDTO getStudent() {
		return student;
	}
	public void setStudent(StudentDTO student) {
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
