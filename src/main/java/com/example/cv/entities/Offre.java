package com.example.cv.entities;

import com.example.cv.sercurityglobal.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "offres")
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer offreId;

    private String titre;
    private String description;

    @ManyToOne
    @JoinColumn(name = "ContractType_ID")
    private ContractType contractType;

    private Double salaire;
    @ManyToOne
    @JoinColumn(name = "Country_ID")
    @JsonBackReference
    private Country country;

    private Date datePublication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @OneToMany(mappedBy = "offre", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Candidature> candidatures;




}

