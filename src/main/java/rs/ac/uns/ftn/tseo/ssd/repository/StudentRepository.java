package rs.ac.uns.ftn.tseo.ssd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.tseo.ssd.model.Korisnik;
import rs.ac.uns.ftn.tseo.ssd.model.Student;

public interface StudentRepository extends JpaRepository <Student, Integer> {
	
	Student findOneByBrojIndexa(String brojIndexa);
	Student findOneByKorisnik(Korisnik korisnik);

}
