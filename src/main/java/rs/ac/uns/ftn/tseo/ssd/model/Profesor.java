package rs.ac.uns.ftn.tseo.ssd.model;

import java.util.HashSet;
import java.util.Set;

public class Profesor {

	private Integer profesorID;
	// -> Korisnik.JMBG
	private Integer JMBG;
	private String zvanje;
	// -> Predaje.profesorID
	private Set<Predmet> predmeti = new HashSet<Predmet>();
	
}
