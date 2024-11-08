package com.example.cv.sercurityglobal.entity;

import com.example.cv.entities.Candidature;
import com.example.cv.entities.Cv;
import com.example.cv.entities.Notification;
import com.example.cv.entities.Offre;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;




@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cv> cvs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Candidature> candidatures;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Offre> offres;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Notification> notifications;


}
