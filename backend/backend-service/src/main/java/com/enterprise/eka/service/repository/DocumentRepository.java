package com.enterprise.eka.service.repository;

import com.enterprise.eka.service.entity.Document;
import com.enterprise.eka.api.common.DocumentClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByDocumentType(String documentType);
    List<Document> findByClassificationIn(List<DocumentClassification> classifications);
    List<Document> findByDepartmentId(Long departmentId);
    List<Document> findByUploadedBy(Long uploadedBy);
}
