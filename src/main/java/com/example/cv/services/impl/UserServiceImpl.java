package com.example.cv.services.impl;

import com.example.cv.dto.*;
import com.example.cv.entities.*;
import com.example.cv.repositories.*;
import com.example.cv.sercurityglobal.dto.UserDetailsDTO;
import com.example.cv.sercurityglobal.entity.User;
import com.example.cv.sercurityglobal.repository.UserRepository;
import com.example.cv.services.NotificationService;
import com.example.cv.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CandidatureRepository candidacyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OffreRepository offerRepository;

    @Autowired
    private CandidatureStatutRepository candidatureStatutRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public List<CandidatureStatutDTO> getAllStatuses() {
        List<CandidatureStatut> statuses = candidatureStatutRepository.findAll();
        return statuses.stream()
                .map(status -> new CandidatureStatutDTO(status.getStatutId(), status.getNomStatut()))
                .collect(Collectors.toList());
    }



    //khaso it3awd man lawl darouri
    @Override
    public Candidature applyForOffer(Long offerId, String username, String email, String cvPath) {
        User candidate = userRepository.findByUserNameAndEmail(username, email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setNom(username);
                    newUser.setEmail(email);
                    return userRepository.save(newUser);
                });

        Offre offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        Candidature candidature = new Candidature();
        candidature.setUser(candidate);
        candidature.setOffre(offer);
        candidature.setDateApplication(new Date());
        candidature.setCv(cvPath);

        // Save the candidature
        candidature = candidacyRepository.save(candidature);

        // Notify the recruiter
        User recruiter = offer.getUser();  // Assuming 'user' in 'Offre' is the recruiter
        String message = "New application for your offer: " + offer.getTitre();
        notificationService.createNotification(recruiter, message, "APPLICATION");

        return candidature;
    }

    @Override
    public List<OffreDTO> getAllOffers() {
        List<Offre> offers = offerRepository.findAll();
        return offers.stream().map(this::convertToOfferDTO).collect(Collectors.toList());
    }

    @Override
    public List<OffreDTO> searchOffers(String title, String countryName) {
        List<Offre> offers = offerRepository.searchOffers(title, countryName);
        return offers.stream().map(this::convertToOfferDTO).collect(Collectors.toList());
    }

    @Override
    public OffreDTO getOfferById(Long id) {
        Offre offer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
        return convertToOfferDTO(offer);
    }

    private OffreDTO convertToOfferDTO(Offre offer) {
        OffreDTO offerDTO = new OffreDTO();
        offerDTO.setOfferID(offer.getOffreId());
        offerDTO.setTitle(offer.getTitre());
        offerDTO.setDescription(offer.getDescription());
        offerDTO.setSalaire(offer.getSalaire());
        offerDTO.setDatePublication(offer.getDatePublication());
        offerDTO.setContractTypeId(offerDTO.getContractTypeId());
        offerDTO.setContractTypeName(offerDTO.getContractTypeName());
        offerDTO.setCountryID(offerDTO.getCountryID());
        offerDTO.setCountryName(offerDTO.getCountryName());

        return offerDTO;
    }


    @Override
    public List<CandidatureDTO> getAllCandidacies(String nom, String email) {
        User user = userRepository.findByUserNameAndEmail(nom, email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Candidature> candidacies = candidacyRepository.findByUser(user);
        return candidacies.stream()
                .map(this::convertToCandidacyDTO)
                .collect(Collectors.toList());
    }

    private CandidatureDTO convertToCandidacyDTO(Candidature candidature) {
        CandidatureDTO candidatureDTO = new CandidatureDTO();
        candidatureDTO.setCandidatureId(candidature.getCandidatureId());
        candidatureDTO.setDateApplication(candidature.getDateApplication());

        if (candidature.getStatut() != null) {
            CandidatureStatutDTO statusDTO = new CandidatureStatutDTO();
            statusDTO.setStatutId(candidature.getStatut().getStatutId());
            statusDTO.setNomStatut(candidature.getStatut().getNomStatut());
            candidatureDTO.setStatut(statusDTO);
        }

        if (candidature.getUser() != null) {
            UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
            userDetailsDTO.setUserId(userDetailsDTO.getUserId());
            userDetailsDTO.setNom(candidature.getUser().getNom());
            userDetailsDTO.setEmail(candidature.getUser().getEmail());
            candidatureDTO.setUser(userDetailsDTO);
        }

        if (candidature.getOffre() != null) {
            OffreDTO offerDTO = new OffreDTO();
            offerDTO.setOfferID(candidature.getOffre().getOffreId());
            offerDTO.setTitle(candidature.getOffre().getTitre());
            candidatureDTO.setOffre(offerDTO);
        }

        return candidatureDTO;
    }

    //    @Override
//    public void updateStatus(Integer candidacyId, Integer statusId) {
//        Candidature candidacy = candidacyRepository.findById(candidacyId)
//                .orElseThrow(() -> new RuntimeException("Candidacy not found"));
//
//        CandidatureStatut status = candidatureStatutRepository.findById(statusId)
//                .orElseThrow(() -> new RuntimeException("Status not found"));
//
//        candidacy.setStatut(status);
//        candidacyRepository.save(candidacy);
//    }

}
