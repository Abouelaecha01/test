package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ref_Level_Formation")
public class LevelFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long levelFormationID;
    private String levelFormationName;

}
