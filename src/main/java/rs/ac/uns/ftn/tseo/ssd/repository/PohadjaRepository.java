package rs.ac.uns.ftn.tseo.ssd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.tseo.ssd.model.Pohadja;
import rs.ac.uns.ftn.tseo.ssd.model.Predmet;

public interface PohadjaRepository extends JpaRepository <Pohadja,Integer> {
	
	Pohadja findByBrojIndexa(Integer brojIndexa);
	List<Pohadja> findByPredmetId(Predmet predmetID);
 
}
