package com.mpsedc.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mpsedc.entity.Document;

public interface DocumentService {
	public Document saveDocument(Long userId, MultipartFile file) throws IOException;

	public List<Document> getAllDocuments();

	public List<Document> getDocumentsByUserId(Long userId);

	public void deleteDocument(Long id);
}
