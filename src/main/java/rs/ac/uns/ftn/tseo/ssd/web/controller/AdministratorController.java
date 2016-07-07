package rs.ac.uns.ftn.tseo.ssd.web.controller;

import java.util.ArrayList;
import java.util.List;

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

import rs.ac.uns.ftn.tseo.ssd.model.Administrator;
import rs.ac.uns.ftn.tseo.ssd.model.Korisnik;
import rs.ac.uns.ftn.tseo.ssd.service.AdministratorService;
import rs.ac.uns.ftn.tseo.ssd.service.KorisnikService;
import rs.ac.uns.ftn.tseo.ssd.web.dto.AdministratorDTO;
import rs.ac.uns.ftn.tseo.ssd.web.dto.KorisnikDTO;

@RestController
@RequestMapping(value="api/administratori")
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private KorisnikService korisnikService;

	//Get all
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<AdministratorDTO>> getAllAdmins(){
		List<Administrator> admins=administratorService.findAll();
		List<AdministratorDTO> adminsDTO=new ArrayList<>();
		for (Administrator a : admins) {
			adminsDTO.add(new AdministratorDTO(a));
		}
		return new ResponseEntity<>(adminsDTO, HttpStatus.OK);
	}
	
	//Get page
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AdministratorDTO>> getAdminsPage(Pageable page){
		Page<Administrator> admini=administratorService.findAll(page);
		List<AdministratorDTO> adminDTO=new ArrayList<>();
		for (Administrator a: admini) {
			adminDTO.add(new AdministratorDTO(a));
		}
		return new ResponseEntity<>(adminDTO, HttpStatus.OK);
		
	}
	
	//Get one
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AdministratorDTO> getAdmin(@PathVariable Integer id){
		Administrator admin=administratorService.findOne(id);
		if(admin==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<>(new AdministratorDTO(admin), HttpStatus.OK);
	}
	
	//Create
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<AdministratorDTO> createAdmin(@RequestBody AdministratorDTO adminDTO){
		Administrator admin=new Administrator();
		
		Korisnik kor=new Korisnik();
		//admin.korisnik.jmbg
		kor.setJMBG(adminDTO.getKorisnik().getJMBG());
		kor.setBrojTelefona(adminDTO.getKorisnik().getBrojTelefona());
		kor.setEmail(adminDTO.getKorisnik().getEmail());
		kor.setKorisnickoIme(adminDTO.getKorisnik().getKorisnickoIme());
		kor.setLozinka(adminDTO.getKorisnik().getLozinka());
		kor.setMesto(adminDTO.getKorisnik().getMesto());
		kor.setPostanskiBroj(adminDTO.getKorisnik().getPostanskiBroj());
		kor.setUlicaIBroj(adminDTO.getKorisnik().getUlicaIBroj());
		kor.setIme(adminDTO.getKorisnik().getIme());
		kor.setPrezime(adminDTO.getKorisnik().getPrezime());
		
		korisnikService.save(kor);
		
		admin.setKorisnik(kor);
		administratorService.save(admin);
		
		return new ResponseEntity<>(new AdministratorDTO(admin), HttpStatus.CREATED);
				
	}
	
	//Update
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<AdministratorDTO> updateAdmin(@RequestBody AdministratorDTO adminDTO){
		Administrator admin= administratorService.findOne(adminDTO.getAdminID());
		if(admin==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Korisnik kor=korisnikService.findOne(admin.getKorisnik().getKorisnikID());
		kor.setJMBG(adminDTO.getKorisnik().getJMBG());
		kor.setBrojTelefona(adminDTO.getKorisnik().getBrojTelefona());
		kor.setEmail(adminDTO.getKorisnik().getEmail());
		kor.setKorisnickoIme(adminDTO.getKorisnik().getKorisnickoIme());
		kor.setLozinka(adminDTO.getKorisnik().getLozinka());
		kor.setMesto(adminDTO.getKorisnik().getMesto());
		kor.setPostanskiBroj(adminDTO.getKorisnik().getPostanskiBroj());
		kor.setUlicaIBroj(adminDTO.getKorisnik().getUlicaIBroj());
		kor.setIme(adminDTO.getKorisnik().getIme());
		kor.setPrezime(adminDTO.getKorisnik().getPrezime());
		
		korisnikService.save(kor);
		admin.setKorisnik(kor);
		administratorService.save(admin);
		return new ResponseEntity<>(new AdministratorDTO(admin), HttpStatus.OK);
		
	}
	
	//Delete
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id){
		Administrator admin=administratorService.findOne(id);
		if(admin!=null){
			administratorService.remove(id);
			korisnikService.remove(admin.getKorisnik().getKorisnikID());
			return new ResponseEntity<>(HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<AdministratorDTO> findOneByUsernameAndPassword(@RequestBody KorisnikDTO korisnikDTO){
		Korisnik kor=korisnikService.findOneByKorisnickoImeAndLozinka(korisnikDTO.getKorisnickoIme(), korisnikDTO.getLozinka());
		//Korisnik kor=korisnikService.findOneByKorisnickoImeAndLozinka(studentDTO.getKorisnik().getKorisnickoIme(), studentDTO.getKorisnik().getLozinka());
		if(kor!=null){
			Administrator a = administratorService.findOneByKorisnik(kor);
			if(a!=null){
				return new ResponseEntity<>(new AdministratorDTO(a), HttpStatus.OK);
			}else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
}
