package rs.ac.uns.ftn.tseo.ssd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.tseo.ssd.model.Korisnik;

public interface KorisnikRepository extends JpaRepository <Korisnik,Long> {
	Korisnik findByImeAndPrezime(String Ime, String Prezime);
	Korisnik findByKorisnickoIme(String korisnickoIme);

}