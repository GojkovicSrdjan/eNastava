package rs.ac.uns.ftn.tseo.ssd.model;

import java.util.HashSet;
import java.util.Set;

public class Pohadja {

	private Integer pohadjaID;
	// -> Student.predmeti
	private Integer brojIndexa;
	// -> Predmet.studenti
	private Integer predmetID;
	// -> Obaveza.pohadjaID
	private Set<Obaveza> obaveze = new HashSet<Obaveza>();
	
}
