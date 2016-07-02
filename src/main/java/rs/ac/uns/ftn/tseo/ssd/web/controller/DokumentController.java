package rs.ac.uns.ftn.tseo.ssd.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import rs.ac.uns.ftn.tseo.ssd.model.Dokument;
import rs.ac.uns.ftn.tseo.ssd.service.DokumentService;
import rs.ac.uns.ftn.tseo.ssd.service.StudentService;
import rs.ac.uns.ftn.tseo.ssd.web.dto.DokumentDTO;

@RestController
@RequestMapping(value="api/dokumenti")
public class DokumentController {
	@Autowired
	private DokumentService dokService;
	@Autowired
	private StudentService studentService;
	
	//Get one
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<DokumentDTO> getDocumetn(@PathVariable Integer id){
		Dokument dok=dokService.findOne(id);
		if(dok==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<>(new DokumentDTO(dok), HttpStatus.OK);
		
	}

	/**
     * Adds a document to the archive.
     * 
     * Url: /archive/upload?file={file}&person={person}&date={date} [POST]
     * 
     * @param file A file posted in a multipart request
     * @param person The name of the uploading person
     * @param date The date of the document
     * @return The meta data of the added document
     */
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public @ResponseBody DocumentMetadata handleFileUpload(
//            @RequestParam(value="file", required=true) MultipartFile file ,
//            @RequestParam(value="person", required=true) String person,
//            @RequestParam(value="date", required=true) @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
//        
//        try {
//            Document document = new Document(file.getBytes(), file.getOriginalFilename(), date, person );
//            getArchiveService().save(document);
//            return document.getMetadata();
//        } catch (RuntimeException e) {
//            LOG.error("Error while uploading.", e);
//            throw e;
//        } catch (Exception e) {
//            LOG.error("Error while uploading.", e);
//            throw new RuntimeException(e);
//        }      
//    }
    
    //Add new document to student
  	@RequestMapping(method=RequestMethod.POST)
  	public ResponseEntity<DokumentDTO> createDocument(
  			@RequestParam(value="file", required=true) MultipartFile file,
  			@RequestParam(value="nazivDokumenta", required=true) String naziv,
  			@RequestParam(value="tipDokumenta", required=true) String tip,
  			@RequestParam(value="studentID", required=true) String studentID
  			){
  		Dokument dok=new Dokument();
  		
  		
//  		dok.setNaziv(dokDTO.getNaziv());
//  		dok.setPutanjaDoDokumenta(dokDTO.getPutanjaDoDokumenta());
//  		dok.setTip(dokDTO.getTip());
//  		dok.setStudent(studentService.findOne(dokDTO.getStudent().getStudentID()));
//  		dokService.save(dok);
  		return new ResponseEntity<>(new DokumentDTO(dok), HttpStatus.CREATED);
  	}
	//Update document
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<DokumentDTO> updateDocumenthandleFileUpload(
  			@RequestParam(value="file", required=true) MultipartFile file,
  			@RequestParam(value="nazivDokumenta", required=true) String naziv,
  			@RequestParam(value="tipDokumenta", required=true) String tip,
  			@RequestParam(value="studentID", required=true) String studentID,
  			@RequestParam(value="dokumentID", required=true) String dokumentID
  			){
		Dokument dok=dokService.findOne(Integer.parseInt(dokumentID));
		if(dok==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		dok.setNaziv(naziv);
		//dok.setPutanjaDoDokumenta(dokDTO.getPutanjaDoDokumenta());
		dok.setTip(tip);
		dokService.save(dok);
		return new ResponseEntity<>(new DokumentDTO(dok), HttpStatus.OK);
		
		
	}
	
	//Remove document
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteDocument(@PathVariable Integer id){
		Dokument dok=dokService.findOne(id);
		if(dok!=null){
			dokService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

}
