package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ref_Display_Language")
public class DisplayLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long displayLanguageID;

    private String displayLanguageName;
}
