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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.tseo.ssd.model.ERacun;
import rs.ac.uns.ftn.tseo.ssd.model.Korisnik;
import rs.ac.uns.ftn.tseo.ssd.model.Pohadja;
import rs.ac.uns.ftn.tseo.ssd.model.Student;
import rs.ac.uns.ftn.tseo.ssd.service.ERacunService;
import rs.ac.uns.ftn.tseo.ssd.service.KorisnikService;
import rs.ac.uns.ftn.tseo.ssd.service.StudentService;
import rs.ac.uns.ftn.tseo.ssd.web.dto.KorisnikDTO;
import rs.ac.uns.ftn.tseo.ssd.web.dto.PohadjaDTO;
import rs.ac.uns.ftn.tseo.ssd.web.dto.PredmetDTO;
import rs.ac.uns.ftn.tseo.ssd.web.dto.StudentDTO;

@RestController
@RequestMapping(value="api/studeni")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private KorisnikService korisnikService;
	@Autowired
	private ERacunService eRacunService;
	
	//GET ALL
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getAllStudenti() {
		List<Student> students = studentService.findAll();
		//convert students to DTOs
		List<StudentDTO> studentsDTO = new ArrayList<>();
		for (Student s : students) {
			studentsDTO.add(new StudentDTO(s));
		}
		return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
	}
	
	// GET STUDENT PAGE
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getStudentiPage(Pageable page) {
		//page object holds data about pagination and sorting
		//the object is created based on the url parameters "page", "size" and "sort" 
		Page<Student> students = studentService.findAll(page);
		
		//convert students to DTOs
		List<StudentDTO> studentsDTO = new ArrayList<>();
		for (Student s : students) {
			studentsDTO.add(new StudentDTO(s));
		}
		return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
	}
	
	// GET ONE STUDENT
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<StudentDTO> getStudent(@PathVariable Integer id){
		Student student = studentService.findOne(id);
		if(student == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);
	}
	
	// CREATE
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO,
			@RequestBody KorisnikDTO korisnikDTO){
		//Potvrdi lozinku?
		Korisnik korisnik = new Korisnik( 
				korisnikDTO.getJMBG(), 
				korisnikDTO.getKorisnickoIme(), 
				korisnikDTO.getLozinka(), 
				korisnikDTO.getIme(), 
				korisnikDTO.getPrezime(), 
				korisnikDTO.getBrojTelefona(), 
				korisnikDTO.getEmail(), 
				korisnikDTO.getUlicaIBroj(), 
				korisnikDTO.getPostanskiBroj(), 
				korisnikDTO.getMesto());
		korisnik = korisnikService.save(korisnik);
		
		ERacun eRacun = new ERacun();
		eRacun.setStanjeNaERacunu(0.00);
		eRacun = eRacunService.save(eRacun);
		
		Student student = new Student();
		student.setBrojIndexa(studentDTO.getBrojIndexa());
		student.setKorisnik(korisnik);
		student.seteRacun(eRacun);
		
		student = studentService.save(student);
		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.CREATED);	
	}
	
	// UPDATE
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO,
			@RequestBody KorisnikDTO korisnikDTO){
		//a student must exist
		Student student = studentService.findOne(studentDTO.getStudentID()); 
		if (student == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Korisnik korisnik = korisnikService.findOne(student.getKorisnik().getKorisnikID());
		korisnik.setJMBG(korisnikDTO.getJMBG()); 
		korisnik.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
		korisnik.setLozinka(korisnikDTO.getLozinka());
		korisnik.setIme(korisnikDTO.getIme());
		korisnik.setPrezime(korisnikDTO.getPrezime()); 
		korisnik.setBrojTelefona(korisnikDTO.getBrojTelefona()); 
		korisnik.setEmail(korisnikDTO.getEmail());
		korisnik.setUlicaIBroj(korisnikDTO.getUlicaIBroj()); 
		korisnik.setPostanskiBroj(korisnikDTO.getPostanskiBroj()); 
		korisnik.setMesto(korisnikDTO.getMesto());
		korisnikService.save(korisnik);
		
		student.setBrojIndexa(student.getBrojIndexa());
		
		student = studentService.save(student);
		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);	
	}
	
	// DELETE
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteStudent(@PathVariable Integer id){
		Student student = studentService.findOne(id);
		if (student != null){
			studentService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// FIND ONE STUDENT by brojIndexa
	@RequestMapping(value="/brojIndexa", method=RequestMethod.GET)
	public ResponseEntity<StudentDTO> getStudentByBrojIndeksa(
			@RequestParam String brojIndexa) {
		Student student = studentService.findOneByBrojIndexa(brojIndexa);
		if(student == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);
	}
	
	// FIND ALL STUDENTI by prezime
	@RequestMapping(value = "/prezime", method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getStudentsByLastName(
			@RequestParam String prezime) {
		List<Student> students = studentService.findAllByPrezime(prezime);
		//convert students to DTOs
		List<StudentDTO> studentsDTO = new ArrayList<>();
		for (Student s : students) {
			studentsDTO.add(new StudentDTO(s));
		}
		return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
	}	
	
	//
	@RequestMapping(value = "/{studentID}/predmeti", method = RequestMethod.GET)
	public ResponseEntity<List<PohadjaDTO>> getStudentCourses(
			@PathVariable Integer studentID) {
		Student student = studentService.findOne(studentID);
		Set<Pohadja> pohadjanja = student.getPohadjanja();
		List<PohadjaDTO> pohadjanjaDTO = new ArrayList<>();
		for (Pohadja pohadja: pohadjanja) {
			PohadjaDTO pohadjaDTO = new PohadjaDTO();
			pohadjaDTO.setPohadjaID(pohadja.getPohadjaID());
			pohadjaDTO.setPredmet(new PredmetDTO(pohadja.getPredmet()));
			//we leave student field empty
			
			pohadjanjaDTO.add(pohadjaDTO);
		}
		return new ResponseEntity<>(pohadjanjaDTO, HttpStatus.OK);
	}
}
