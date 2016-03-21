package rs.ac.uns.ftn.tseo.ssd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.tseo.ssd.model.Predaje;
import rs.ac.uns.ftn.tseo.ssd.model.Predmet;
import rs.ac.uns.ftn.tseo.ssd.model.Profesor;

public interface PredajeRepository extends JpaRepository <Predaje,Integer>{
	
	List<Predaje> findAllByPredmet(Predmet predmet);
	List<Predaje> findAllByProfesor(Profesor profesor);
	
}
