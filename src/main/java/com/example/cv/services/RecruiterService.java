package com.example.cv.services;

import com.example.cv.dto.CandidatureDTO;
import com.example.cv.dto.OffreDTO;
import com.example.cv.entities.Offre;
import com.example.cv.sercurityglobal.entity.User;

import java.util.List;

public interface RecruiterService {

    Offre createOffer(OffreDTO offreDTO, User recruteur);

    List<CandidatureDTO> getCandidaciesForRecruiter(Integer recruiterId); // Added method

    // Method bach y-modifi status dyal candidature
    void updateCandidacyStatus(Integer candidacyId, Integer newStatusId);


}
