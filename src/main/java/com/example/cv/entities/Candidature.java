package com.example.cv.entities;

import com.example.cv.sercurityglobal.entity.User;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Data
@Entity(name = "candidatures")
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer candidatureId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offreId", referencedColumnName = "offreId")
    private Offre offre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statutId", referencedColumnName = "statutId")
    private CandidatureStatut statut;

    private Date dateApplication;

    private String cv;
}