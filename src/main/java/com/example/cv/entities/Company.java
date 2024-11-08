package com.example.cv.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Ref_Company")
public class Company {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long companyID;
    private String companyName;
    private String address;
    private String tel;
    private String typeOfCompany;
    @Lob
    @Column(name = "company_Image", columnDefinition = "MEDIUMBLOB")
    private byte[] companyImage;
//    @OneToMany(mappedBy = "company")
//    @JsonManagedReference
//    private List<Recruiter> recruiters;
//
//    @OneToMany(mappedBy = "company")
//    @JsonManagedReference
//    private List<Candidature> candidacyListings;
//
//    @OneToMany(mappedBy = "company")
//    @JsonManagedReference
//    private List<Offre> offers;
}