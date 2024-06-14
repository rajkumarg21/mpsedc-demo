package com.mpsedc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mpsedc.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	List<Document> findByUserId(Long userId);

	Document getDocumentById(Long documentId);
}
