package rs.ac.uns.ftn.tseo.ssd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.tseo.ssd.model.Predaje;
import rs.ac.uns.ftn.tseo.ssd.model.Predmet;
import rs.ac.uns.ftn.tseo.ssd.model.Profesor;
import rs.ac.uns.ftn.tseo.ssd.repository.PredajeRepository;

@Service
public class PredajeService {
	@Autowired
	PredajeRepository predajeRepo;
	
	public Predaje findOne(Integer predajeID){
		return predajeRepo.findOne(predajeID);
	}
	
	public List<Predaje> findAll(){
		return predajeRepo.findAll();
	}
	
	public Predaje save(Predaje p){
		return predajeRepo.save(p);
	}
	
	public void remove(Integer predajeID){
		predajeRepo.delete(predajeID);
	}
	
	public List<Predaje> findByPredmetID (Predmet predmetID){
		return predajeRepo.findByPredmetID(predmetID);
	}
	
	public List<Predaje> findByProfesorID (Profesor profesorID){
		return predajeRepo.findByProfesorID(profesorID);
	}
	
	public Predaje findByTipPredavanja (String tipPredavanja){
		return predajeRepo.findByTipPredavanja(tipPredavanja);
	}
}
