package rs.ac.uns.ftn.tseo.ssd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.tseo.ssd.model.Student;

public interface StudentRepository extends JpaRepository <Student, Integer> {
	
	Student findOneByBrojIndexa(String brojIndexa);
	List<Student> findAllByPrezime(String prezime);

}
