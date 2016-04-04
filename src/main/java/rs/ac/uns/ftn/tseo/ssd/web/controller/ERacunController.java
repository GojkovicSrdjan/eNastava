package rs.ac.uns.ftn.tseo.ssd.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.tseo.ssd.model.ERacun;
import rs.ac.uns.ftn.tseo.ssd.model.Uplata;
import rs.ac.uns.ftn.tseo.ssd.service.ERacunService;
import rs.ac.uns.ftn.tseo.ssd.web.dto.ERacunDTO;
import rs.ac.uns.ftn.tseo.ssd.web.dto.UplataDTO;

@RestController
@RequestMapping(value="api/eracun")
public class ERacunController {
	@Autowired
	private ERacunService eRacunService;
	
	//Get one
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ERacunDTO> getERacun(@PathVariable Integer id){
		ERacun eRacun=eRacunService.findOne(id);
		if(eRacun==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(new ERacunDTO(eRacun), HttpStatus.OK);
	}
	
	//Update
	@RequestMapping(consumes="application/json", method=RequestMethod.PUT)
	public ResponseEntity<ERacunDTO> updateERacun(@RequestBody ERacunDTO eRacunDTO){
		ERacun eRacun=eRacunService.findOne(eRacunDTO.geteRacunID());
		if(eRacun==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		eRacun.setStanjeNaERacunu(eRacunDTO.getStanjeNaERacunu());
		eRacunService.save(eRacun);
		
		return new ResponseEntity<>(new ERacunDTO(eRacun), HttpStatus.OK);
	}
	
	//Get ERacun payments
	@RequestMapping(value="/{id}/uplate", method=RequestMethod.GET)
	public ResponseEntity<List<UplataDTO>> getERacunPayments(@PathVariable Integer id){
		ERacun eRacun=eRacunService.findOne(id);
		if(eRacun==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Set<Uplata> uplate=eRacun.getUplate();
		List<UplataDTO> uplateDTO=new ArrayList<>();
		
		for (Uplata u : uplate) {
			UplataDTO uDTO=new UplataDTO();
			uDTO.setUplataID(u.getUplataID());
			uDTO.setDatum(u.getDatum());
			uDTO.setIznos(u.getIznos());
			uDTO.setSvrha(u.getSvrha());
			
			uplateDTO.add(uDTO);		
		}
		
		return new ResponseEntity<>(uplateDTO, HttpStatus.OK);
	}
	
}
