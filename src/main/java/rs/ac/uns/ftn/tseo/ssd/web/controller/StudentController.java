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

import rs.ac.uns.ftn.tseo.ssd.model.Dokument;
import rs.ac.uns.ftn.tseo.ssd.model.ERacun;
import rs.ac.uns.ftn.tseo.ssd.model.Korisnik;
import rs.ac.uns.ftn.tseo.ssd.model.Obaveza;
import rs.ac.uns.ftn.tseo.ssd.model.Pohadja;
import rs.ac.uns.ftn.tseo.ssd.model.Student;
import rs.ac.uns.ftn.tseo.ssd.model.Uplata;
import rs.ac.uns.ftn.tseo.ssd.service.DokumentService;
import rs.ac.uns.ftn.tseo.ssd.service.ERacunService;
import rs.ac.uns.ftn.tseo.ssd.service.KorisnikService;
import rs.ac.uns.ftn.tseo.ssd.service.ObavezaService;
import rs.ac.uns.ftn.tseo.ssd.service.PohadjaService;
import rs.ac.uns.ftn.tseo.ssd.service.StudentService;
import rs.ac.uns.ftn.tseo.ssd.service.UplataService;
import rs.ac.uns.ftn.tseo.ssd.web.dto.DokumentDTO;
import rs.ac.uns.ftn.tseo.ssd.web.dto.ObavezaDTO;
import rs.ac.uns.ftn.tseo.ssd.web.dto.PohadjaDTO;
import rs.ac.uns.ftn.tseo.ssd.web.dto.PredmetDTO;
import rs.ac.uns.ftn.tseo.ssd.web.dto.StudentDTO;
import rs.ac.uns.ftn.tseo.ssd.web.dto.TipObavezeDTO;

@RestController
@RequestMapping(value="api/studenti")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private KorisnikService korisnikService;
	@Autowired
	private ERacunService eRacunService;
	@Autowired
	private ObavezaService obavezaService;
	@Autowired
	private DokumentService dokumentService;
	@Autowired
	private UplataService uplataService;
	@Autowired
	private PohadjaService pohadjaService;
	
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
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO){
		//Potvrdi lozinku?
		Korisnik kor = new Korisnik();

		kor.setJMBG(studentDTO.getKorisnik().getJMBG());
		kor.setBrojTelefona(studentDTO.getKorisnik().getBrojTelefona());
		kor.setEmail(studentDTO.getKorisnik().getEmail());
		kor.setKorisnickoIme(studentDTO.getKorisnik().getKorisnickoIme());
		kor.setLozinka(studentDTO.getKorisnik().getLozinka());
		kor.setMesto(studentDTO.getKorisnik().getMesto());
		kor.setPostanskiBroj(studentDTO.getKorisnik().getPostanskiBroj());
		kor.setUlicaIBroj(studentDTO.getKorisnik().getUlicaIBroj());
		kor.setIme(studentDTO.getKorisnik().getIme());
		kor.setPrezime(studentDTO.getKorisnik().getPrezime());
		
		
		kor = korisnikService.save(kor);
		
		ERacun eRacun = new ERacun();
		eRacun.setStanjeNaERacunu(0.00);
		eRacun = eRacunService.save(eRacun);
		
		Student student = new Student();
		student.setBrojIndexa(studentDTO.getBrojIndexa());
		student.setKorisnik(kor);
		student.seteRacun(eRacun);
		
		student = studentService.save(student);
		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.CREATED);	
	}
	
	// UPDATE
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO){
		//a student must exist
		Student student = studentService.findOne(studentDTO.getStudentID()); 
		if (student == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Korisnik kor = korisnikService.findOne(student.getKorisnik().getKorisnikID());
		kor.setJMBG(studentDTO.getKorisnik().getJMBG());
		kor.setBrojTelefona(studentDTO.getKorisnik().getBrojTelefona());
		kor.setEmail(studentDTO.getKorisnik().getEmail());
		kor.setKorisnickoIme(studentDTO.getKorisnik().getKorisnickoIme());
		kor.setLozinka(studentDTO.getKorisnik().getLozinka());
		kor.setMesto(studentDTO.getKorisnik().getMesto());
		kor.setPostanskiBroj(studentDTO.getKorisnik().getPostanskiBroj());
		kor.setUlicaIBroj(studentDTO.getKorisnik().getUlicaIBroj());
		kor.setIme(studentDTO.getKorisnik().getIme());
		kor.setPrezime(studentDTO.getKorisnik().getPrezime());
		
		korisnikService.save(kor);
		
		student.setBrojIndexa(student.getBrojIndexa());
		
		student = studentService.save(student);
		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);	
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
	
	//All student documents
	@RequestMapping(value="/{id}/dokumenti", method=RequestMethod.GET)
	public ResponseEntity<List<DokumentDTO>> getStudentDocuments(@PathVariable Integer id){
		Student s=studentService.findOne(id);
		if(s==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Set<Dokument> dokumenta=s.getDokumenta();
		List<DokumentDTO> dokumentaDTO=new ArrayList<>();
		for (Dokument d : dokumenta) {
			DokumentDTO dokDTO=new DokumentDTO();
			dokDTO.setDokumentID(d.getDokumentID());
			dokDTO.setNaziv(d.getNaziv());
			dokDTO.setPutanjaDoDokumenta(d.getPutanjaDoDokumenta());
			dokDTO.setTip(d.getTip());
			dokumentaDTO.add(dokDTO);
		}
		return new ResponseEntity<>(dokumentaDTO, HttpStatus.OK);
		
		
		
	}
	
	//All student exams
	@RequestMapping(value="/{studentID}/obaveze", method=RequestMethod.GET)
	public ResponseEntity<List<ObavezaDTO>> getStudentExams(@PathVariable Integer id){
		Student s=studentService.findOne(id);
		if(s==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Set<Obaveza> obaveze=s.getObaveze();
		List<ObavezaDTO> obavezeDTO=new ArrayList<>();
		for (Obaveza o: obaveze) {
			ObavezaDTO oDTO=new ObavezaDTO();
			oDTO.setBrojBodova(o.getBrojBodova());
			oDTO.setObavezaID(o.getObavezaID());
			oDTO.setOcena(o.getOcena());
			oDTO.setPolozeno(o.getPolozeno());
			oDTO.setTipObaveze(new TipObavezeDTO(o.getTipObaveze()));
			obavezeDTO.add(oDTO);
			
		}
		return new ResponseEntity<>(obavezeDTO, HttpStatus.OK);	
		
	}
	
	// DELETE
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteStudent(@PathVariable Integer id){
		Student student = studentService.findOne(id);
		if (student != null){
			
			//pohadjanja
			for (Pohadja p : student.getPohadjanja()){
				pohadjaService.remove(p.getPohadjaID());
			}
			
			//dokumenta
			for (Dokument d : student.getDokumenta()){
				dokumentService.remove(d.getDokumentID());
			}
			
			//obaveze
			for (Obaveza o : student.getObaveze()){
				obavezaService.remove(o.getObavezaID());
			}
			
			//eRacun(posle brisanja studenta) i uplate
			for (Uplata u : student.geteRacun().getUplate()){
				uplataService.remove(u.getUplataID());
			}
			
			studentService.remove(id);
			
			//Kada se obrise student brise se i korisnik
			korisnikService.remove(student.getKorisnik().getKorisnikID());
			//i eRacun
			eRacunService.remove(student.geteRacun().geteRacunID());
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
//	//Dodavanje novog studentovog dokumenta
//	@RequestMapping(value = "/{id}/dokument", method=RequestMethod.POST)
//	public ResponseEntity<DokumentDTO> addDocumentToStudent(@RequestParam Integer id,
//			@RequestBody DokumentDTO dokDTO){
//		Student s=studentService.findOne(id);
//		if(s==null)
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		
//		Dokument dok=new Dokument();
//		
//		dok.setNaziv(dokDTO.getNaziv());
//		dok.setPutanjaDoDokumenta(dokDTO.getPutanjaDoDokumenta());
//		dok.setTip(dokDTO.getTip());
//		dok.setStudent(s);
//		dokService.save(dok);
//		
//		return new ResponseEntity<>(new DokumentDTO(dok), HttpStatus.CREATED);
//	}
}
