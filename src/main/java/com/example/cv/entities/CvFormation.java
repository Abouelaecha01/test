package com.example.cv.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Cv_Formation")
public class CvFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvFormationID;

    @ManyToOne
    @JoinColumn(name = "cvID")
    @JsonIgnore
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "LevelFormation_ID") //todo
    private LevelFormation levelFormation;

    @ManyToOne
    @JoinColumn(name = "Mention_ID")
    private Mention mention;

    @ManyToOne
    @JoinColumn(name = "School_ID") //todo makhashach tkon référentiel
    private School school;

    @ManyToOne
    @JoinColumn(name = "Country_ID")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "City_ID") //todo
    private City city;


    private String formationTitle; //todo

    private Date dateDebut;
    private Date dateFin;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
