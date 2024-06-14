package com.mpsedc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.mpsedc.entity.Document;
import com.mpsedc.repository.DocumentRepository;
import com.mpsedc.service.DocumentService;

import java.io.IOException;

import java.util.List;
/*
 
 Author: Rajkumar saad
  date: 06/06/2024

*/

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "*")
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@Autowired
	private DocumentRepository documentRepository;

	// upload document
	@PostMapping("/upload/{userId}")
	public ResponseEntity<Document> uploadDocument(@PathVariable Long userId, @RequestParam("file") MultipartFile file)
			throws IOException {
		return ResponseEntity.ok(documentService.saveDocument(userId, file));
	}

	// get document list for particular user by user id
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Document>> getDocumentsByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(documentService.getDocumentsByUserId(userId));
	}

	// get all document
	@GetMapping
	public ResponseEntity<List<Document>> getAllDocuments() {
		return ResponseEntity.ok(documentService.getAllDocuments());
	}

	// delete uploaded document by document id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
		documentService.deleteDocument(id);
		return ResponseEntity.noContent().build();
	}

	// view document in format like jpg, jpeg,gif, pdf etc.
	@GetMapping("/view/{documentId}")
	public ResponseEntity<byte[]> getDocument(@PathVariable Long documentId) {
		Document document = documentRepository.findById(documentId)
				.orElseThrow(() -> new RuntimeException("Document not found"));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(document.getType()));
		headers.setContentDispositionFormData("inline", document.getName());

		return new ResponseEntity<>(document.getData(), headers, HttpStatus.OK);
	}
}
