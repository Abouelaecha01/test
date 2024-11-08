package com.example.cv.services;

import com.example.cv.dto.CandidatureDTO;
import com.example.cv.dto.CandidatureStatutDTO;
import com.example.cv.dto.OffreDTO;
import com.example.cv.entities.Candidature;

import java.util.List;

public interface UserService {
    @PreAuthorize("hasAuthority('VIEW_OFFERS')")
    List<OffreDTO> getAllOffers();

    @PreAuthorize("hasAuthority('SEARCH_OFFERS')")
    List<OffreDTO> searchOffers(String title, String countryName);

    @PreAuthorize("hasAuthority('VIEW_OFFER_DETAILS')")
    OffreDTO getOfferById(Long id);

    @PreAuthorize("hasAuthority('APPLY_OFFER')")
    Candidature applyForOffer(Long offerId, String nom, String email, String cvPath);

    @PreAuthorize("hasAuthority('VIEW_CANDIDACIES')")
    List<CandidatureDTO> getAllCandidacies(String nom, String email);

//    @PreAuthorize("hasAuthority('UPDATE_CANDIDACY_STATUS')")
//    void updateStatus(Integer candidacyId, Integer statusId);

    @PreAuthorize("hasAuthority('VIEW_STATUSES')")
    List<CandidatureStatutDTO> getAllStatuses();
}
