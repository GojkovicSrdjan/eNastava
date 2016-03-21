package rs.ac.uns.ftn.tseo.ssd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.tseo.ssd.model.Dokument;
import rs.ac.uns.ftn.tseo.ssd.repository.DokumentRepository;

@Service
public class DokumentService {
	
	@Autowired
	DokumentRepository dokRepo;
	
	public Dokument findOne(Integer dokumentID){
		return dokRepo.findOne(dokumentID);
	}
	
	public List<Dokument> findAll(){
		return dokRepo.findAll();
	}
	
	public Dokument save(Dokument dok){
		return dokRepo.save(dok);
	}
	
	public void remove(Integer dokumentID){
		dokRepo.delete(dokumentID);
	}
	
	public Dokument findByNaziv(String naziv){
		return dokRepo.findByNaziv(naziv);
	}
	
	public List<Dokument> findByTip(String tip){
		return dokRepo.findByTip(tip);
	}
	
}
