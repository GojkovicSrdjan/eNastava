package rs.ac.uns.ftn.tseo.ssd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.tseo.ssd.model.Profesor;

public interface ProfesorRepository extends JpaRepository <Profesor, Long> {
	Profesor findByZvanje (String Zvanje);
	

}
