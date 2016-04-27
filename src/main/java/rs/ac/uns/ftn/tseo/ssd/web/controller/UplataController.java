package rs.ac.uns.ftn.tseo.ssd.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.tseo.ssd.model.Uplata;
import rs.ac.uns.ftn.tseo.ssd.service.ERacunService;
import rs.ac.uns.ftn.tseo.ssd.service.UplataService;
import rs.ac.uns.ftn.tseo.ssd.web.dto.UplataDTO;

@RestController
@RequestMapping(value="api/uplate")
public class UplataController {
	@Autowired
	private UplataService uplataService;
	@Autowired
	private ERacunService eRacunService;
	
	
	//Get one
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UplataDTO> getUplata(@PathVariable Integer id){
		Uplata u=uplataService.findOne(id);
		if(u==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(new UplataDTO(u), HttpStatus.OK);
	}
	
	//Create
	@RequestMapping(consumes="application/json", method=RequestMethod.POST)
	public ResponseEntity<UplataDTO> createUplata(@RequestBody UplataDTO uplataDTO){
		Uplata u=new Uplata();
		
		u.setDatum(uplataDTO.getDatum());
		u.seteRacun(eRacunService.findOne(uplataDTO.geteRacun().geteRacunID()));
		u.setIznos(uplataDTO.getIznos());
		u.setSvrha(uplataDTO.getSvrha());
		
		uplataService.save(u);
		
		return new ResponseEntity<>(new UplataDTO(u), HttpStatus.CREATED);
	}
	
	//Update
	@RequestMapping(consumes="application/json", method=RequestMethod.PUT)
	public ResponseEntity<UplataDTO> updateUplata(@RequestBody UplataDTO uplataDTO){
		Uplata u=uplataService.findOne(uplataDTO.getUplataID());
		
		if(u==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		u.setDatum(uplataDTO.getDatum());
		u.setIznos(uplataDTO.getIznos());
		u.setSvrha(uplataDTO.getSvrha());
		
		uplataService.save(u);
		
		return new ResponseEntity<>(new UplataDTO(u), HttpStatus.OK);
		
	}

	
	//Delete
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUplata(@PathVariable Integer id){
		Uplata u=uplataService.findOne(id);
		
		if(u!=null){
			uplataService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
}
