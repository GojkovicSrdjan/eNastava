package rs.ac.uns.ftn.tseo.ssd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.tseo.ssd.model.Pohadja;
import rs.ac.uns.ftn.tseo.ssd.model.Predmet;
import rs.ac.uns.ftn.tseo.ssd.repository.PohadjaRepository;

@Service
public class PohadjaService {
	@Autowired
	PohadjaRepository pohadjaRepo;
	
	
	public Pohadja findOne(Integer pohadjaID){
		return pohadjaRepo.findOne(pohadjaID);
	}
	
	public List<Pohadja> findAll(){
		return pohadjaRepo.findAll();
	}
	
	public Pohadja save(Pohadja p){
		return pohadjaRepo.save(p);
	}
	
	public void remove(Integer pohadjaID){
		pohadjaRepo.delete(pohadjaID);
	}
	
	public Pohadja findByBrojIndexa(Integer brojIndexa){
		return pohadjaRepo.findByBrojIndexa(brojIndexa);
	}
	
	public List<Pohadja> findByPredmetId(Predmet predmetID){
		return pohadjaRepo.findByPredmetId(predmetID);
	}
}
