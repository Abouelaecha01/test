package com.example.cv.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Cv_Experience")
public class CvExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvExperienceID;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cvID")
    private Cv cv;

    @ManyToOne
    @JoinColumn(name ="Company_ID")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "Country_ID") //todo
    private Country country;

    private Date dateDebut;
    private Date dateFin;
    private String description;
    private String fonction;
    private Date createdAt;
    private Date updatedAt;
}
