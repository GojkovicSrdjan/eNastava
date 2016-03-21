package rs.ac.uns.ftn.tseo.ssd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.tseo.ssd.model.Pohadja;
import rs.ac.uns.ftn.tseo.ssd.model.Predmet;

public interface PohadjaRepository extends JpaRepository <Pohadja,Long> {
	
	Pohadja findByBrojIDexa(Integer BrojIndexa);
	List<Pohadja> findByPredmetId(Predmet PredmetID);
 
}
