package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ref_Level_Language")
public class LevelLanguage {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)

    private Long levelLanguageID;
    private String levelLanguageName;

}
