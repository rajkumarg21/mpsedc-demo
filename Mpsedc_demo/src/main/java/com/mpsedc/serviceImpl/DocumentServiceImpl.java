package com.mpsedc.serviceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mpsedc.entity.Document;
import com.mpsedc.entity.User;
import com.mpsedc.repository.DocumentRepository;
import com.mpsedc.repository.UserRepository;
import com.mpsedc.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private UserRepository userRepository;

	public Document saveDocument(Long userId,MultipartFile file) throws IOException {
		 User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Document document = new Document();
		document.setName(file.getOriginalFilename());
		document.setType(file.getContentType());
		document.setData(file.getBytes());
		document.setUploadDate(new Date());
		 document.setUser(user);
		return documentRepository.save(document);
	}

	public List<Document> getAllDocuments() {
		return documentRepository.findAll();
	}
	public List<Document> getDocumentsByUserId(Long userId) {
        return documentRepository.findByUserId(userId);
    }
	public void deleteDocument(Long id) {
		documentRepository.deleteById(id);
	}
}
