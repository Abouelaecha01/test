package com.example.cv.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Cv_Language")
public class CvLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvLanguagesID;
    
    @ManyToOne
    @JoinColumn(name = "cvID")
    @JsonIgnore
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "Language_ID")
    private Language language;

    @ManyToOne
    @JoinColumn(name ="levelLanguage_ID")
    private LevelLanguage levelLanguage;

    private Date createdAt;
    private Date updatedAt;

}
