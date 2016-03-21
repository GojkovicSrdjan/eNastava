package rs.ac.uns.ftn.tseo.ssd.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.tseo.ssd.model.Obaveza;

public interface ObavezaRepository extends JpaRepository <Obaveza,Integer>{
	
	Obaveza findByDatum(Date datum);

}
