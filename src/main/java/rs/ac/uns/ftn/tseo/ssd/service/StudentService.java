package rs.ac.uns.ftn.tseo.ssd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.tseo.ssd.model.Student;
import rs.ac.uns.ftn.tseo.ssd.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepo;
	
	public Student findOne(Integer studentID){
		return studentRepo.findOne(studentID);
	}
	
	public List<Student> findAll(){
		return studentRepo.findAll();
	}
	
	public Student save(Student s){
		return studentRepo.save(s);
	}
	
	public void remove(Integer studentID){
		studentRepo.delete(studentID);
	}
	
	public Student findByBrojIndexa(String brojIndexa){
		return studentRepo.findOneByBrojIndexa(brojIndexa);
	}
	
}
