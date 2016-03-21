package rs.ac.uns.ftn.tseo.ssd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.tseo.ssd.model.Obaveza;
import rs.ac.uns.ftn.tseo.ssd.model.Pohadja;
import rs.ac.uns.ftn.tseo.ssd.repository.ObavezaRepository;

@Service
public class ObavezaService {

	@Autowired
	ObavezaRepository obavezaRepo;
	
	public Obaveza findOne(Integer obavezaID){
		return obavezaRepo.findOne(obavezaID);
	}
	
	public List<Obaveza> findAll(){
		return obavezaRepo.findAll();
	}
	
	public Obaveza save(Obaveza o){
		return obavezaRepo.save(o);
	}
	
	public void remove(Integer obavezaID){
		obavezaRepo.delete(obavezaID);
	}
	
	public Obaveza findOneByPohadja(Pohadja pohadja){
		return obavezaRepo.findOneByPohadja(pohadja);
	}

}
