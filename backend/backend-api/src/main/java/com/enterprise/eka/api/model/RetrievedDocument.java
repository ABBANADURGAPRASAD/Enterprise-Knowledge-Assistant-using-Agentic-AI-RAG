package com.enterprise.eka.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RetrievedDocument {
    private Long documentId;
    private String documentTitle;
    private String content;
    private double score;
    private String classification;
    private String documentType;
}
