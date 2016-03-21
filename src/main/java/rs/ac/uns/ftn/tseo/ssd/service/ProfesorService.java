package rs.ac.uns.ftn.tseo.ssd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.tseo.ssd.model.Profesor;
import rs.ac.uns.ftn.tseo.ssd.repository.ProfesorRepository;

@Service
public class ProfesorService {
	@Autowired
	ProfesorRepository profRepo;
	
	public Profesor findOne(Integer profesorID){
		return profRepo.findOne(profesorID);
	}
	
	public List<Profesor> findAll(){
		return profRepo.findAll();
	}
	
	public Profesor save(Profesor p){
		return profRepo.save(p);
	}
	
	public void remove(Integer profesorID){
		profRepo.delete(profesorID);
	}
	
	public Profesor findByZvanje (String zvanje){
		return profRepo.findByZvanje(zvanje);
	}
}
