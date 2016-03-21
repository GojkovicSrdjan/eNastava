package rs.ac.uns.ftn.tseo.ssd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.tseo.ssd.model.Administrator;

public interface AdministratorRepository extends JpaRepository <Administrator,Long> {
	
	Administrator findByJmbg(Integer Jmbg);


}
