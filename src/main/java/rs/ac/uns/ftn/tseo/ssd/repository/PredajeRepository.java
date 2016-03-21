package rs.ac.uns.ftn.tseo.ssd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.tseo.ssd.model.Predaje;
import rs.ac.uns.ftn.tseo.ssd.model.Predmet;
import rs.ac.uns.ftn.tseo.ssd.model.Profesor;

public interface PredajeRepository extends JpaRepository <Predaje,Integer>{
	
	List<Predaje> findByPredmetID (Predmet predmetID);
	List<Predaje> findByProfesorID (Profesor profesorID);
	Predaje findByTipPredavanja (String tipPredavanja); 

}
