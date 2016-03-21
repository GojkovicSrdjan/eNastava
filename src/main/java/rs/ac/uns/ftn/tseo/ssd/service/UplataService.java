package rs.ac.uns.ftn.tseo.ssd.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.tseo.ssd.model.ERacun;
import rs.ac.uns.ftn.tseo.ssd.model.Uplata;
import rs.ac.uns.ftn.tseo.ssd.repository.UplataRepository;

@Service
public class UplataService {
	
	@Autowired
	UplataRepository uplataRepo;
	
	public Uplata findOne(Integer uplataID){
		return uplataRepo.findOne(uplataID);
	}
	
	public List<Uplata> findAll(){
		return uplataRepo.findAll();
	}
	
	public Uplata save(Uplata u){
		return uplataRepo.save(u);
	}
	
	public void remove(Integer uplataID){
		uplataRepo.delete(uplataID);
	}
	
	public List<Uplata> findByERacunID (ERacun eRacunID){
		return uplataRepo.findByERacunID(eRacunID);
	}
	
	public Uplata findByDatum (Date datum){
		return uplataRepo.findByDatum(datum);
	}
	
	public Uplata findBySvrha (String svrha){
		return uplataRepo.findBySvrha(svrha);
	}
	
}
