package rs.ac.uns.ftn.tseo.ssd.model;

import java.util.HashSet;
import java.util.Set;

public class ERacun {

	private Integer eRacunID;
	private Double stanjeNaERacunu;
	// -> Uplata.eRacunID
	private Set<Uplata> uplate = new HashSet<Uplata>();
	
}
