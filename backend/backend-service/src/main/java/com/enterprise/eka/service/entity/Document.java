package com.enterprise.eka.service.entity;

import com.enterprise.eka.api.common.DocumentClassification;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(name = "file_name", length = 500)
    private String fileName;

    @Column(name = "file_type", length = 50)
    private String fileType;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "storage_path", length = 1000)
    private String storagePath;

    @Column(name = "document_type", length = 50)
    private String documentType;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    @Builder.Default
    private DocumentClassification classification = DocumentClassification.INTERNAL;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "uploaded_by")
    private Long uploadedBy;

    @Column(length = 20)
    @Builder.Default
    private String status = "PENDING";

    @Column(name = "chunk_count")
    @Builder.Default
    private Integer chunkCount = 0;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
