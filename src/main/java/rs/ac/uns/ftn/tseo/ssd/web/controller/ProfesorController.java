package rs.ac.uns.ftn.tseo.ssd.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.tseo.ssd.model.Korisnik;
import rs.ac.uns.ftn.tseo.ssd.model.Predaje;
import rs.ac.uns.ftn.tseo.ssd.model.Profesor;
import rs.ac.uns.ftn.tseo.ssd.service.KorisnikService;
import rs.ac.uns.ftn.tseo.ssd.service.ProfesorService;
import rs.ac.uns.ftn.tseo.ssd.web.dto.PredajeDTO;
import rs.ac.uns.ftn.tseo.ssd.web.dto.PredmetDTO;
import rs.ac.uns.ftn.tseo.ssd.web.dto.ProfesorDTO;

@RestController
@RequestMapping(value="api/profesori")
public class ProfesorController {
	
	@Autowired
	private ProfesorService profService;
	@Autowired
	private KorisnikService korService;
	
	//Get all
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<ProfesorDTO>> getAllProfesori(){
		List<Profesor> profesor=profService.findAll();
		List<ProfesorDTO> profesorDTO=new ArrayList<>();
		for (Profesor p: profesor) {
			profesorDTO.add(new ProfesorDTO(p));
			
		}
		return new ResponseEntity<>(profesorDTO, HttpStatus.OK);
	}
	
	//Get page
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ProfesorDTO>> getProfesoriPage(Pageable page){
		Page<Profesor> profesori=profService.findAll(page);
		List<ProfesorDTO> profesorDTO=new ArrayList<>();
		for (Profesor p: profesori) {
			profesorDTO.add(new ProfesorDTO(p));
		}
		return new ResponseEntity<>(profesorDTO, HttpStatus.OK);
		
	}
	
	//Get one 
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ProfesorDTO> getProfesor(@PathVariable Integer id){
		Profesor profesor= profService.findOne(id);
		if(profesor == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new ProfesorDTO(profesor), HttpStatus.OK);
	}
	
	// CREATE
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ProfesorDTO> saveProfesor(@RequestBody ProfesorDTO profesorDTO){
		//Potvrdi lozinku?
		Korisnik kor = new Korisnik();

		kor.setJMBG(profesorDTO.getKorisnik().getJMBG());
		kor.setBrojTelefona(profesorDTO.getKorisnik().getBrojTelefona());
		kor.setEmail(profesorDTO.getKorisnik().getEmail());
		kor.setKorisnickoIme(profesorDTO.getKorisnik().getKorisnickoIme());
		kor.setLozinka(profesorDTO.getKorisnik().getLozinka());
		kor.setMesto(profesorDTO.getKorisnik().getMesto());
		kor.setPostanskiBroj(profesorDTO.getKorisnik().getPostanskiBroj());
		kor.setUlicaIBroj(profesorDTO.getKorisnik().getUlicaIBroj());
		kor.setIme(profesorDTO.getKorisnik().getIme());
		kor.setPrezime(profesorDTO.getKorisnik().getPrezime());
		
		
		korService.save(kor);
		Profesor p=new Profesor();
		p.setZvanje(profesorDTO.getZvanje());
		p.setKorisnik(kor);
		profService.save(p);
		
		return new ResponseEntity<>(new ProfesorDTO(p), HttpStatus.CREATED);	
	}
	
	// UPDATE
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<ProfesorDTO> updateProfesor(@RequestBody ProfesorDTO profesorDTO){
		Profesor p=profService.findOne(profesorDTO.getProfesorID());
		if(p==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Korisnik kor = korService.findOne(profesorDTO.getKorisnik().getKorisnikID());

		kor.setJMBG(profesorDTO.getKorisnik().getJMBG());
		kor.setBrojTelefona(profesorDTO.getKorisnik().getBrojTelefona());
		kor.setEmail(profesorDTO.getKorisnik().getEmail());
		kor.setKorisnickoIme(profesorDTO.getKorisnik().getKorisnickoIme());
		kor.setLozinka(profesorDTO.getKorisnik().getLozinka());
		kor.setMesto(profesorDTO.getKorisnik().getMesto());
		kor.setPostanskiBroj(profesorDTO.getKorisnik().getPostanskiBroj());
		kor.setUlicaIBroj(profesorDTO.getKorisnik().getUlicaIBroj());
		kor.setIme(profesorDTO.getKorisnik().getIme());
		kor.setPrezime(profesorDTO.getKorisnik().getPrezime());
		
		korService.save(kor);
		
		p.setZvanje(profesorDTO.getZvanje());
		
		profService.save(p);
		
		return new ResponseEntity<>(new ProfesorDTO(p), HttpStatus.OK);	
	}
	
	//Delete
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProfesor(@PathVariable Integer id){
		Profesor p = profService.findOne(id);
		if (p != null){
			profService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	//All teacher courses
	@RequestMapping(value="/{id}/predmeti", method=RequestMethod.GET)
	public ResponseEntity<List<PredajeDTO>> getPredmetTeachers(@PathVariable Integer id){
		Profesor p = profService.findOne(id);
		Set<Predaje> predavanja=p.getPredavanja();
		List<PredajeDTO> predavanjaDTO=new ArrayList<>();
		for (Predaje predaje: predavanja) {
			PredajeDTO predajeDTO=new PredajeDTO();
			predajeDTO.setPredajeID(predaje.getPredajeID());
			predajeDTO.setPredmet(new PredmetDTO(predaje.getPredmet()));
			predajeDTO.setTipPredavanja(predaje.getTipPredavanja());
			predajeDTO.setUloga(predaje.getUloga());
			
			predavanjaDTO.add(predajeDTO);
		}
		
		return new ResponseEntity<>(predavanjaDTO, HttpStatus.OK);
	}
}
