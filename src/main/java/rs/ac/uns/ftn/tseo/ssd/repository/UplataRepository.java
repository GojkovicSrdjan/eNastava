package rs.ac.uns.ftn.tseo.ssd.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.tseo.ssd.model.ERacun;
import rs.ac.uns.ftn.tseo.ssd.model.Uplata;

public interface UplataRepository extends JpaRepository <Uplata, Integer> {
	
	List<Uplata> findByERacunID (ERacun eRacunID);
	Uplata findByDatum (Date datum);
	Uplata findBySvrha (String svrha);
	}
