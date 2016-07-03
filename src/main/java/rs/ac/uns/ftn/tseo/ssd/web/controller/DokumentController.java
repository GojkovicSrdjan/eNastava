package rs.ac.uns.ftn.tseo.ssd.web.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

import rs.ac.uns.ftn.tseo.ssd.model.Dokument;
import rs.ac.uns.ftn.tseo.ssd.model.Student;
import rs.ac.uns.ftn.tseo.ssd.service.DokumentService;
import rs.ac.uns.ftn.tseo.ssd.service.StudentService;
import rs.ac.uns.ftn.tseo.ssd.web.dto.DokumentDTO;

@RestController
@RequestMapping(value="api/dokumenti")
public class DokumentController {
	@Autowired
	private DokumentService dokumentService;
	@Autowired
	private StudentService studentService;
	
	private static final Logger LOG = Logger.getLogger(DokumentController.class);
	
	//Get one
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<DokumentDTO> getDocumetn(@PathVariable Integer id){
		Dokument dok=dokumentService.findOne(id);
		if(dok==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<>(new DokumentDTO(dok), HttpStatus.OK);
		
	}
    
	
	
    //Add new document to student
  	@RequestMapping(method=RequestMethod.POST)
  	public ResponseEntity<DokumentDTO> createDocument(
  			@RequestParam(value="file", required=true) MultipartFile file,
  			@RequestParam(value="nazivDokumenta", required=true) String naziv,
  			@RequestParam(value="tipDokumenta", required=true) String tip,
  			@RequestParam(value="studentID", required=true) String studentID
  			){
  		try {
  			Dokument dokument = new Dokument();
  	  		Student student = studentService.findOne(Integer.parseInt(studentID));
  	  		dokument.setStudent(student);
  	  	
  	  		//priprema nove putanje
  		    String folderPath = "static/uploads/dokumenti/";
  		    String fileUUID = UUID.randomUUID().toString(); //dokument cuvamo pod nazivom
  			String ext = Files.getFileExtension(file.getOriginalFilename());
  			String putanjaDoDokumenta = folderPath+fileUUID+"."+ext;
  			
  			dokument.setPutanjaDoDokumenta(putanjaDoDokumenta);
  	  		dokument.setNaziv(naziv);
  			dokument.setTip(tip);
  			
  			dokumentService.save(dokument);
  	  		
  			//cuvanje novog fajla
  			String filePathString = "src/main/webapp/static/uploads/dokumenti/"+fileUUID+"."+ext;
  			String filePath = new File(filePathString).getAbsolutePath();
  			File dest = new File(filePath);
            file.transferTo(dest);
  			
  			dokumentService.save(dokument);
  	  		return new ResponseEntity<>(new DokumentDTO(dokument), HttpStatus.CREATED);
  		} catch (RuntimeException e) {
  		  LOG.error("Error while uploading.", e);
          throw e;
  		} catch (Exception e) {
          LOG.error("Error while uploading.", e);
          throw new RuntimeException(e);
  		}
  		
  	}
  	
  	
  	  
  	// Update document - change metadata
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<DokumentDTO> updateDocumenthandleFileUpload(
  			@RequestParam(value="nazivDokumenta", required=true) String naziv,
  			@RequestParam(value="tipDokumenta", required=true) String tip,
  			@RequestParam(value="dokumentID", required=true) String dokumentID
  			){
		try {
  			Dokument dokument = dokumentService.findOne(Integer.parseInt(dokumentID)); 
  	  		
  	  		dokument.setNaziv(naziv);
  			dokument.setTip(tip);
  			dokumentService.save(dokument);
  	  		return new ResponseEntity<>(new DokumentDTO(dokument), HttpStatus.OK); 			
  		} catch (RuntimeException e) {
  		  LOG.error("Error while uploading.", e);
          throw e;
  		} catch (Exception e) {
          LOG.error("Error while uploading.", e);
          throw new RuntimeException(e);
  		}
	}
	// Update document - upload new file
	@RequestMapping(value="/upload",method=RequestMethod.PUT)
  	public ResponseEntity<DokumentDTO> createUpdateDocument(
  			@RequestParam(value="file", required=true) MultipartFile file,
  			@RequestParam(value="nazivDokumenta", required=true) String naziv,
  			@RequestParam(value="tipDokumenta", required=true) String tip,
  			@RequestParam(value="dokumentID", required=true) String dokumentID
  			){
  		try {
  			Dokument dokument = dokumentService.findOne(Integer.parseInt(dokumentID));
  			String staraPutanja = dokument.getPutanjaDoDokumenta();
  			
  	  		//priprema nove putanje
  		    String folderPath = "static/uploads/dokumenti/";
  		    String fileUUID = UUID.randomUUID().toString(); //dokument cuvamo pod nazivom
  			String ext = Files.getFileExtension(file.getOriginalFilename());
  			String putanjaDoDokumenta = folderPath+fileUUID+"."+ext;
  			
  			dokument.setPutanjaDoDokumenta(putanjaDoDokumenta);
  	  		dokument.setNaziv(naziv);
  			dokument.setTip(tip);
  			
  			dokumentService.save(dokument);
  	  		
  			//cuvanje novog fajla
  			String filePathString = "src/main/webapp/static/uploads/dokumenti/"+fileUUID+"."+ext;
  			String filePath = new File(filePathString).getAbsolutePath();
  			File dest = new File(filePath);
            file.transferTo(dest);
  	  		
  	  		//brisanje starog fajla
            System.out.println(staraPutanja);
            if(staraPutanja!=null && !staraPutanja.trim().equalsIgnoreCase("")){
            	Path pathPath = Paths.get(new File("src/main/webapp/"+staraPutanja).getAbsolutePath());
      			java.nio.file.Files.delete(pathPath);
            }
  			
  			return new ResponseEntity<>(new DokumentDTO(dokument), HttpStatus.OK);
  		} catch (RuntimeException e) {
  		  LOG.error("Error while uploading.", e);
          throw e;
  		} catch (Exception e) {
          LOG.error("Error while uploading.", e);
          throw new RuntimeException(e);
  		}
  		
  	}
	
	
	
	//Remove document
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteDocument(@PathVariable Integer id){
		Dokument dok=dokumentService.findOne(id);
		if(dok!=null){
			dokumentService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

}
