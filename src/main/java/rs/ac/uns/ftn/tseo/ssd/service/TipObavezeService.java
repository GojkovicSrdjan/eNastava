package rs.ac.uns.ftn.tseo.ssd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.tseo.ssd.model.TipObaveze;
import rs.ac.uns.ftn.tseo.ssd.repository.TipObavezeReoisitory;

@Service
public class TipObavezeService {

	@Autowired
	TipObavezeReoisitory tipObavezeRepo;
	
	public TipObaveze findOne(Integer tipObavezeID){
		return tipObavezeRepo.findOne(tipObavezeID);
	}
	
	public List<TipObaveze> findAll(){
		return tipObavezeRepo.findAll();
	}
	
	public TipObaveze save(TipObaveze to){
		return tipObavezeRepo.save(to);
	}
	
	public void remove(Integer tipObavezeID){
		tipObavezeRepo.delete(tipObavezeID);
	}
	
	
}
