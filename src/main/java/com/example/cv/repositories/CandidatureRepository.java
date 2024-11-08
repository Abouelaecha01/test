package com.example.cv.repositories;

import com.example.cv.entities.Candidature;
import com.example.cv.entities.Offre;
import com.example.cv.sercurityglobal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatureRepository extends JpaRepository<Candidature, Integer> {
    // Trouver les candidatures associées à une offre spécifique
    List<Candidature> findByOffre(Offre offre);

    List<Candidature> findByUser(User user);

}
