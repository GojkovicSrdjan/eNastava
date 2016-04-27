package rs.ac.uns.ftn.tseo.ssd.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.tseo.ssd.model.Predaje;
import rs.ac.uns.ftn.tseo.ssd.model.Predmet;
import rs.ac.uns.ftn.tseo.ssd.model.Profesor;
import rs.ac.uns.ftn.tseo.ssd.service.PredajeService;
import rs.ac.uns.ftn.tseo.ssd.service.PredmetService;
import rs.ac.uns.ftn.tseo.ssd.service.ProfesorService;
import rs.ac.uns.ftn.tseo.ssd.web.dto.PredajeDTO;

@RestController
@RequestMapping(value="api/predaje")
public class PredajeController {
	@Autowired
	private PredajeService predajeService;
	@Autowired
	private PredmetService predmetService;
	@Autowired
	private ProfesorService profesorService;
	
	//Create
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<PredajeDTO> createPredaje(@RequestBody PredajeDTO predajeDTO){
		if(predajeDTO.getPredmet()==null || predajeDTO.getProfesor()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Predmet pred=predmetService.findOne(predajeDTO.getPredmet().getPredmetID());
		Profesor prof=profesorService.findOne(predajeDTO.getProfesor().getProfesorID());
		
		if(pred==null || prof==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Predaje predaje=new Predaje();
		predaje.setPredmet(pred);
		predaje.setProfesor(prof);
		predaje.setTipPredavanja(predajeDTO.getTipPredavanja());
		predaje.setUloga(predajeDTO.getUloga());
		
		predajeService.save(predaje);
		return new ResponseEntity<>(new PredajeDTO(predaje), HttpStatus.CREATED);
	}
	
	//Update
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<PredajeDTO> updatePredaje(@RequestBody PredajeDTO predajeDTO){
		Predaje predaje=predajeService.findOne(predajeDTO.getPredajeID());
		if(predaje==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		
		predaje.setUloga(predajeDTO.getUloga());
		predaje.setTipPredavanja(predajeDTO.getTipPredavanja());
		predajeService.save(predaje);
		return new ResponseEntity<>(new PredajeDTO(predaje), HttpStatus.OK);
		
	}
	//Delete
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletePredaje(@PathVariable Integer id){
		Predaje p=predajeService.findOne(id);
		if(p!=null){
			predajeService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
