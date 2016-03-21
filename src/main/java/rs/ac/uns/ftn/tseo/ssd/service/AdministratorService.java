package rs.ac.uns.ftn.tseo.ssd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.tseo.ssd.model.Administrator;
import rs.ac.uns.ftn.tseo.ssd.repository.AdministratorRepository;

@Service
public class AdministratorService {
	@Autowired
	AdministratorRepository adminRepo;
	
	public Administrator findOne(Integer adminID){
		return adminRepo.findOne(adminID);
	}
	
	public List<Administrator> findAll(){
		return adminRepo.findAll();
	}
	
	public Administrator save(Administrator a){
		return adminRepo.save(a);
	}
	
	public void remove(Integer adminID){
		adminRepo.delete(adminID);
	}
	
}
