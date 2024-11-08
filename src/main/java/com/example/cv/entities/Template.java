package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ref_Template")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long templateID;

    private String templateName; // templateName

    @Lob
    @Column(name = "template_Image", columnDefinition = "MEDIUMBLOB")
    private byte[] templateImage; // templateImage


    private String templateData; // .docx
}
