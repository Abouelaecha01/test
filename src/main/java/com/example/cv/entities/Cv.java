package com.example.cv.entities;

import com.example.cv.sercurityglobal.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "CV_Info")

public class Cv {
    @Column(name = "cvID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long cvID;
    private String cvRef;

    @PrePersist
    @PreUpdate
    private void generateUniqueReference() {

        String toEncode = email + "-" + UUID.randomUUID().toString();
        this.cvRef = Base64.getEncoder().encodeToString(toEncode.getBytes());

    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    @OneToMany(mappedBy = "cv")
    private List<CvExperience> cvExperiences;

    @OneToMany(mappedBy = "cv") // kola cv 3ando bzaf dyal cv_skill
    private List<CvSkill> cvSkills;

    @OneToMany(mappedBy = "cv")
    private List<CvLanguage> cvLanguages;

    @OneToMany(mappedBy = "cv")
    private List<CvHobby> cvHobbies;

    @OneToMany(mappedBy = "cv")
    private List<CvFormation> cvFormations;

    @OneToMany(mappedBy = "cv")
    private List<CvCertificate> cvCertificates;

    @OneToOne(mappedBy = "cv")
    private CvDisplayLanguage cvDisplayLanguage;

    @ManyToOne
    @JoinColumn(name = "templateID")
    private Template template;

    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "profile")
    private String profile;

    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private byte[] image;

    @Column(name = "dateDeNaissance")
    private Date dateDeNaissance;

    @Column(unique = true)
    private String email;
    @Column(name = "tel1")
    private String tel1;
    @Column(name = "fixmobile")
    private String fixmobile;
    @Column(name = "address")
    private String address;
    @Column(name = "linkedin")
    private String linkedin;
    @Column(name = "createdAt")
    private Date createdAt;
    @Column(name = "updatedAt")
    private Date updatedAt;

}