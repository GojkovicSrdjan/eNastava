package rs.ac.uns.ftn.tseo.ssd.web.dto;

import rs.ac.uns.ftn.tseo.ssd.model.Pohadja;

public class PohadjaDTO {

	private Integer pohadjaID;
	private StudentDTO student;
	private PredmetDTO predmet;
	
	
	
	public PohadjaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PohadjaDTO(Pohadja pohadja){
		pohadjaID = pohadja.getPohadjaID();
		student = new StudentDTO(pohadja.getStudent());
		predmet = new PredmetDTO(pohadja.getPredmet());
	}
	
	public PohadjaDTO(Integer pohadjaID, StudentDTO student, PredmetDTO predmet) {
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
	public StudentDTO getStudent() {
		return student;
	}
	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	public PredmetDTO getPredmet() {
		return predmet;
	}
	public void setPredmet(PredmetDTO predmet) {
		this.predmet = predmet;
	}
	
	
	
}
