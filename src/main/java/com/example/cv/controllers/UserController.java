package com.example.cv.controllers;

import com.example.cv.dto.CandidatureDTO;
import com.example.cv.dto.CandidatureStatutDTO;
import com.example.cv.dto.OffreDTO;
import com.example.cv.entities.Candidature;

import com.example.cv.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    //ran htajha

    // Accessible uniquement aux recruteurs ayant la permission CREATE_OFFER
    @GetMapping("/offers")
    @PreAuthorize("hasRole('ROLE_RECRUITER') and hasAuthority('VIEW_OFFERS')")
    public ResponseEntity<List<OffreDTO>> getAllOffers() {
        List<OffreDTO> offers = userService.getAllOffers();
        return ResponseEntity.ok(offers);
    }


    //ran htajha
    // Accessible aux recruteurs ayant la permission de recherche
    @GetMapping("/offers/search")
    @PreAuthorize("hasRole('ROLE_RECRUITER') and hasAuthority('SEARCH_OFFERS')")
    public ResponseEntity<List<OffreDTO>> searchOffers(@RequestParam(required = false) String title,
                                                       @RequestParam(required = false) String countryName) {
        List<OffreDTO> offers = userService.searchOffers(title, countryName);
        return ResponseEntity.ok(offers);
    }

    //ran htajha
    // Accessible aux candidats ayant la permission de postuler
    @PostMapping("/offers/{offerId}/apply")
    @PreAuthorize("hasRole('ROLE_CANDIDATE') and hasAuthority('APPLY_OFFER')")
    public ResponseEntity<Candidature> applyForOffer(@PathVariable Long offerId, @RequestParam String nom,
                                                     @RequestParam String email, @RequestParam("cv") MultipartFile cvFile) {
        String cvPath = saveCvFile(cvFile);
        Candidature createdCandidacy = userService.applyForOffer(offerId, nom, email, cvPath);
        return new ResponseEntity<>(createdCandidacy, HttpStatus.CREATED);
    }


    //ran htajha
    private String saveCvFile(MultipartFile cvFile) {
        try {
            String fileName = cvFile.getOriginalFilename();
            String uploadDir = "C:/Users/Hamza/uploads/cv-files/";
            Path uploadPath = Paths.get(uploadDir);

            // Debug statement
            System.out.println("Upload Path: " + uploadPath.toString());

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                // Debug statement
                System.out.println("Directory created: " + uploadPath.toString());
            }

            Path filePath = uploadPath.resolve(fileName);
            cvFile.transferTo(filePath.toFile());

            // Debug statement
            System.out.println("File saved to: " + filePath.toString());

            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not save CV file. Error: " + e.getMessage());
        }
    }

    //ran htajha
    // Accessible aux recruteurs pour voir les candidatures
    @GetMapping("/candidacies")
    @PreAuthorize("hasRole('ROLE_RECRUITER') and hasAuthority('VIEW_CANDIDACIES')")
    public ResponseEntity<List<CandidatureDTO>> getAllCandidacies(@RequestParam String nom, @RequestParam String email) {
        List<CandidatureDTO> candidacies = userService.getAllCandidacies(nom, email);
        return ResponseEntity.ok(candidacies);
    }


    //ran htajha
    // Accessible à tous les utilisateurs ayant la permission de voir les statuts
    @GetMapping("/statuses")
    @PreAuthorize("hasAuthority('VIEW_STATUSES')")
    public ResponseEntity<List<CandidatureStatutDTO>> getAllStatuses() {
        List<CandidatureStatutDTO> statuses = userService.getAllStatuses();
        return ResponseEntity.ok(statuses);
    }

    //ran htajha

    // Accessible à tout utilisateur ayant la permission de voir les détails d'une offre
    @GetMapping("/offers/{id}")
    @PreAuthorize("hasAuthority('VIEW_OFFER_DETAILS')")
    public ResponseEntity<OffreDTO> getOfferById(@PathVariable Long id) {
        OffreDTO offer = userService.getOfferById(id);
        return ResponseEntity.ok(offer);
    }


//    //ran htajha
//    // Accessible aux recruteurs ayant la permission de mettre à jour le statut
//    @PutMapping("/candidacies/{candidacyId}/status")
//    @PreAuthorize("hasRole('ROLE_RECRUITER') and hasAuthority('UPDATE_CANDIDACY_STATUS')")
//    public ResponseEntity<?> updateStatus(@PathVariable Integer candidacyId, @RequestParam Integer statusId) {
//        try {
//            userService.updateStatus(candidacyId, statusId);
//            return ResponseEntity.ok().body("Status updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }



}
