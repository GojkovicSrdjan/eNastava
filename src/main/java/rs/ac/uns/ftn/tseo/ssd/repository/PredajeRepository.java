package rs.ac.uns.ftn.tseo.ssd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.tseo.ssd.model.Predaje;
import rs.ac.uns.ftn.tseo.ssd.model.Predmet;
import rs.ac.uns.ftn.tseo.ssd.model.Profesor;

public interface PredajeRepository extends JpaRepository <Predaje,Long>{
	
	List<Predaje> findByPredmetID (Predmet PredmetID);
	List<Predaje> findByProfesorID (Profesor ProfesorID);
	Predaje findByTipPredavanja (String TipPredavanja); 

}
