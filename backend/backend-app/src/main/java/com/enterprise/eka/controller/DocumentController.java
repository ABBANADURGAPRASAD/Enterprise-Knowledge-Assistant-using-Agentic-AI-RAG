package com.enterprise.eka.controller;

import com.enterprise.eka.api.dto.ApiResponse;
import com.enterprise.eka.service.entity.Document;
import com.enterprise.eka.service.rag.ingestion.DocumentIngestionService;
import com.enterprise.eka.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentIngestionService ingestionService;
    private final UserRepository userRepository;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<Document>> uploadDocument(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "documentType", defaultValue = "GENERAL") String documentType,
            @RequestParam(value = "classification", defaultValue = "INTERNAL") String classification
    ) throws java.io.IOException {
        Long userId = userRepository.findByUsername(userDetails.getUsername())
                .map(u -> u.getId())
                .orElseThrow();

        Document document = ingestionService.ingest(
                file.getBytes(),
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize(),
                documentType,
                classification,
                userId
        );
        return ResponseEntity.ok(ApiResponse.success("Document uploaded and indexed", document));
    }
}
