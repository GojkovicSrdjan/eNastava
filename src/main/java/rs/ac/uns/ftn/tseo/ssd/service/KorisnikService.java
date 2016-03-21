package rs.ac.uns.ftn.tseo.ssd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.tseo.ssd.model.Korisnik;
import rs.ac.uns.ftn.tseo.ssd.repository.KorisnikRepository;

@Service
public class KorisnikService {
	
	@Autowired
	KorisnikRepository korRepo;
	
	public Korisnik findOne(Integer korisnikID){
		return korRepo.findOne(korisnikID);
	}
	
	public List<Korisnik> findAll(){
		return korRepo.findAll();
	}
	
	public Korisnik save(Korisnik k){
		return korRepo.save(k);
	}
	
	public void remove(Integer korisnikID){
		korRepo.delete(korisnikID);
	}
	
	public Korisnik findOneByImeAndPrezime(String ime, String prezime){
		return korRepo.findOneByImeAndPrezime(ime, prezime);
	}
	
	public Korisnik findOneByKorisnickoIme(String korisnickoIme){
		return korRepo.findOneByKorisnickoIme(korisnickoIme);
	}
	
}
