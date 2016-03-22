package rs.ac.uns.ftn.tseo.ssd.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.tseo.ssd.model.Obaveza;
import rs.ac.uns.ftn.tseo.ssd.model.Pohadja;

public interface ObavezaRepository extends JpaRepository <Obaveza,Integer>{
	
}
