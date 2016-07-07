package rs.ac.uns.ftn.tseo.ssd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.tseo.ssd.model.Administrator;
import rs.ac.uns.ftn.tseo.ssd.model.Korisnik;

public interface AdministratorRepository extends JpaRepository <Administrator,Integer> {
	
	Administrator findOneByKorisnik(Korisnik korisnik);
	
}
