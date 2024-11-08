package com.example.cv.services.impl;

import com.example.cv.dto.*;
import com.example.cv.entities.*;
import com.example.cv.repositories.*;
import com.example.cv.sercurityglobal.entity.User;
import com.example.cv.sercurityglobal.repository.UserRepository;
import com.example.cv.services.NotificationService;
import com.example.cv.services.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class RecruiterServiceImpl implements RecruiterService {

    @Autowired
    private OffreRepository offreRepository;
    @Autowired
    private CandidatureRepository candidatureRepository;
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CandidatureStatutRepository candidatureStatutRepository;


    @Autowired
    private ContractTypeRepository contractTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public Offre createOffer(OffreDTO offreDTO, User recruteur) {
        // T-verifier contract type w country
        ContractType contractType = contractTypeRepository.findById(offreDTO.getContractTypeId())
                .orElseThrow(() -> new RuntimeException("Contract Type not found"));
        Country country = countryRepository.findById(offreDTO.getCountryID())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        // T-create l'Offre object
        Offre offre = new Offre();
        offre.setTitre(offreDTO.getTitle());
        offre.setDescription(offreDTO.getDescription());
        offre.setSalaire(offreDTO.getSalaire());
        offre.setContractType(contractType);
        offre.setCountry(country);
        offre.setDatePublication(offreDTO.getDatePublication());
        offre.setUser(recruteur); // T-add user/recruteur li kay-create l-offre

        offre = offreRepository.save(offre);

        // Notify candidates about the new offer
        List<User> candidates = userRepository.findAllByRoleName("ROLE_CANDIDATE");
        String message = "New job offer: " + offre.getTitre() + " is now available!";
        for (User candidate : candidates) {
            notificationService.createNotification(candidate, message, "NEW_OFFER");
        }

        return offre;
    }


    @Override
    public List<CandidatureDTO> getCandidaciesForRecruiter(Integer recruiterId) {
        // Récupérer le recruteur par son ID
        User recruiter = userRepository.findById(recruiterId)
                .orElseThrow(() -> new RuntimeException("Recruteur introuvable"));

        // Récupérer toutes les offres publiées par ce recruteur
        List<Offre> offers = offreRepository.findByUser(recruiter);

        // Récupérer toutes les candidatures associées aux offres du recruteur
        List<Candidature> candidacies = offers.stream()
                .flatMap(offer -> candidatureRepository.findByOffre(offer).stream())
                .collect(Collectors.toList());

        // Convertir les candidatures en DTOs
        return candidacies.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CandidatureDTO convertToDTO(Candidature candidacy) {
        CandidatureDTO dto = new CandidatureDTO();
        dto.setCandidatureId(candidacy.getCandidatureId());
        dto.setDateApplication(candidacy.getDateApplication());


        // Convertir et ajouter le statut
        if (candidacy.getStatut() != null) {
            dto.setStatutId(candidacy.getStatut().getStatutId());
            dto.setNomStatut(candidacy.getStatut().getNomStatut());
        }

        // Ajouter les informations de l'utilisateur (candidat)
        if (candidacy.getUser() != null) {
            dto.setUserId(candidacy.getUser().getUserId());
            dto.setUserName(candidacy.getUser().getNom());
            dto.setUserEmail(candidacy.getUser().getEmail());
        }

        // Ajouter les informations de l'offre
        if (candidacy.getOffre() != null) {
            dto.setOfferId(candidacy.getOffre().getOffreId());
            dto.setOfferTitle(candidacy.getOffre().getTitre());
        }

        return dto;
    }

    @Override
    public void updateCandidacyStatus(Integer candidacyId, Integer newStatusId) {
        Candidature candidature = candidatureRepository.findById(candidacyId)
                .orElseThrow(() -> new RuntimeException("Candidature not found"));
        CandidatureStatut newStatus = candidatureStatutRepository.findById(newStatusId)
                .orElseThrow(() -> new RuntimeException("Status not found"));

        candidature.setStatut(newStatus);
        candidatureRepository.save(candidature);
    }



}
