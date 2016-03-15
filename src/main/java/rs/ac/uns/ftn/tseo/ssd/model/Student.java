package rs.ac.uns.ftn.tseo.ssd.model;

import java.util.Set;
import java.util.HashSet;

public class Student {

	private String brojIndexa;
	// -> Korisnik.JMBG
	private Integer JMBG;
	// -> ERacun.eRacunID
	private Integer eRacunID;
	// -> Dokument.dokumentID
	private Set<Dokument> dokumenta = new HashSet<Dokument>();
	// -> Pohadja.brojIndexa
	private Set<Predmet> predmeti = new HashSet<Predmet>();
	
	
}
