package rs.ac.uns.ftn.tseo.ssd.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.tseo.ssd.model.Obaveza;
import rs.ac.uns.ftn.tseo.ssd.service.ObavezaService;
import rs.ac.uns.ftn.tseo.ssd.service.StudentService;
import rs.ac.uns.ftn.tseo.ssd.service.TipObavezeService;
import rs.ac.uns.ftn.tseo.ssd.web.dto.ObavezaDTO;

@RestController
@RequestMapping(value="api/obaveze")
public class ObavezaController {
	@Autowired
	private ObavezaService obavezaService;
	@Autowired
	private TipObavezeService tipService;
	@Autowired
	private StudentService studentService;
	
	//Get one
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ObavezaDTO> getObaveza(@PathVariable Integer id){
		Obaveza obaveza=obavezaService.findOne(id);
		if(obaveza==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<>(new ObavezaDTO(obaveza), HttpStatus.OK);
	}
	
	//Create
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ObavezaDTO> createObaveza(@RequestBody ObavezaDTO obavezaDTO){
		Obaveza o=new Obaveza();
		o.setBrojBodova(obavezaDTO.getBrojBodova());
		o.setOcena(obavezaDTO.getOcena());
		o.setPolozeno(obavezaDTO.getPolozeno());
		o.setTipObaveze(tipService.findOne(obavezaDTO.getTipObaveze().getTipObavezeID()));
		o.setStudent(studentService.findOne(obavezaDTO.getStudent().getStudentID()));
		
		obavezaService.save(o);
		
		return new ResponseEntity<>(new ObavezaDTO(o), HttpStatus.CREATED);
	}
	
	//Update
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<ObavezaDTO> updateObaveza(@RequestBody ObavezaDTO obavezaDTO){
		Obaveza o=obavezaService.findOne(obavezaDTO.getObavezaID());
		if(o==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		o.setBrojBodova(obavezaDTO.getBrojBodova());
		o.setOcena(obavezaDTO.getOcena());
		o.setPolozeno(obavezaDTO.getPolozeno());
//		o.setTipObaveze(tipService.findOne(obavezaDTO.getTipObaveze().getTipObavezeID()));
//		o.setStudent(studentService.findOne(obavezaDTO.getStudent().getStudentID()));
//		
		obavezaService.save(o);
		
		return new ResponseEntity<>(new ObavezaDTO(o), HttpStatus.CREATED);
	}
	
	//Delete
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteObaveza(@PathVariable Integer id){
		Obaveza obaveza=obavezaService.findOne(id);
		if(obaveza!=null){
			obavezaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
