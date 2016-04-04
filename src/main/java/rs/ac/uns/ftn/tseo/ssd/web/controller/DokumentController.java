package rs.ac.uns.ftn.tseo.ssd.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.tseo.ssd.model.Dokument;
import rs.ac.uns.ftn.tseo.ssd.service.DokumentService;
import rs.ac.uns.ftn.tseo.ssd.service.StudentService;
import rs.ac.uns.ftn.tseo.ssd.web.dto.DokumentDTO;

@RestController
@RequestMapping(value="api/dokumenti")
public class DokumentController {
	@Autowired
	private DokumentService dokService;
	@Autowired
	private StudentService studentService;
	
	//Get one
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<DokumentDTO> getDocumetn(@PathVariable Integer id){
		Dokument dok=dokService.findOne(id);
		if(dok==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<>(new DokumentDTO(dok), HttpStatus.OK);
		
	}

	
	//Update document
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<DokumentDTO> updateDocument(@RequestBody DokumentDTO dokDTO){
		Dokument dok=dokService.findOne(dokDTO.getDokumentID());
		if(dok==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		dok.setNaziv(dokDTO.getNaziv());
		dok.setPutanjaDoDokumenta(dokDTO.getPutanjaDoDokumenta());
		dok.setTip(dokDTO.getTip());
		dokService.save(dok);
		return new ResponseEntity<>(new DokumentDTO(dok), HttpStatus.OK);
		
		
	}
	
	//Remove document
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteDocument(@PathVariable Integer id){
		Dokument dok=dokService.findOne(id);
		if(dok!=null){
			dokService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
	//Add new document to student
	@RequestMapping(consumes="application/json", method=RequestMethod.POST)
	public ResponseEntity<DokumentDTO> createDocument(@RequestBody DokumentDTO dokDTO){
		Dokument dok=new Dokument();
		
		
		dok.setNaziv(dokDTO.getNaziv());
		dok.setPutanjaDoDokumenta(dokDTO.getPutanjaDoDokumenta());
		dok.setTip(dokDTO.getTip());
		dok.setStudent(studentService.findOne(dokDTO.getStudent().getStudentID()));
		dokService.save(dok);
		return new ResponseEntity<>(new DokumentDTO(dok), HttpStatus.CREATED);
	}
	

}
