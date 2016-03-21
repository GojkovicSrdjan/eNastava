package rs.ac.uns.ftn.tseo.ssd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.tseo.ssd.model.Predmet;
import rs.ac.uns.ftn.tseo.ssd.repository.PredmetRepository;

@Service
public class PredmetService {
	@Autowired
	PredmetRepository predRepo;
	
	public Predmet findOne(Integer predmetID){
		return predRepo.findOne(predmetID);
	}
	
	public List<Predmet> findAll(){
		return predRepo.findAll();
	}
	
	public Predmet save(Predmet p){
		return predRepo.save(p);
	}
	
	public void remove(Integer predmetID){
		predRepo.delete(predmetID);
	}
	
	public Predmet findByNaziv (String naziv){
		return predRepo.findByNaziv(naziv);
	}
}
