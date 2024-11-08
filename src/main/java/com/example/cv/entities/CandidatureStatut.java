package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ref_Candidature_Statut")

public class CandidatureStatut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statutId;

    private String nomStatut;
}
