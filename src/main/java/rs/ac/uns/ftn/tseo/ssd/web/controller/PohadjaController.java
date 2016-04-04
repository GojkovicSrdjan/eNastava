package rs.ac.uns.ftn.tseo.ssd.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.tseo.ssd.model.Pohadja;
import rs.ac.uns.ftn.tseo.ssd.model.Predmet;
import rs.ac.uns.ftn.tseo.ssd.model.Student;
import rs.ac.uns.ftn.tseo.ssd.service.PohadjaService;
import rs.ac.uns.ftn.tseo.ssd.service.PredmetService;
import rs.ac.uns.ftn.tseo.ssd.service.StudentService;
import rs.ac.uns.ftn.tseo.ssd.web.dto.PohadjaDTO;

@RestController
@RequestMapping(value="api/pohadja")
public class PohadjaController {
	@Autowired
	private PohadjaService pohadjaService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private PredmetService predmetService;
	
	//Create
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<PohadjaDTO> createPohadjanje(@RequestBody PohadjaDTO pohadjaDTO){
		
		if(pohadjaDTO.getStudent()==null || pohadjaDTO.getPredmet()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Student s=studentService.findOne(pohadjaDTO.getStudent().getStudentID());
		Predmet p=predmetService.findOne(pohadjaDTO.getPredmet().getPredmetID());
		if(s==null || p==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Pohadja pohadja=new Pohadja();
		pohadja.setPredmet(p);
		pohadja.setStudent(s);
		
		pohadjaService.save(pohadja);
		
		return new ResponseEntity<>(new PohadjaDTO(pohadja), HttpStatus.CREATED);
	}
	
	
	//Delete
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Void> deletePohadja(@PathVariable Integer id){
		Pohadja p=pohadjaService.findOne(id);
		if(p!=null){
			pohadjaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}	
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

}
