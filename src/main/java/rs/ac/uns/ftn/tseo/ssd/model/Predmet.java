package rs.ac.uns.ftn.tseo.ssd.model;

import java.util.HashSet;
import java.util.Set;

public class Predmet {

	private Integer predmetID;
	private String naziv;
	private String opis;
	// -> Predaje.predmetID
	private Set<Profesor> profesori = new HashSet<Profesor>();
	// -> Pohadja.predmetID
	private Set<Student> studenti = new HashSet<Student>();
	
}
