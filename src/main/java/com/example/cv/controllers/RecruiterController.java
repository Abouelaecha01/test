package com.example.cv.controllers;

import com.example.cv.dto.CandidatureDTO;
import com.example.cv.dto.OffreDTO;
import com.example.cv.entities.Offre;
import com.example.cv.sercurityglobal.entity.User;
import com.example.cv.services.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
    @RequestMapping("/recruiter")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;


    @PreAuthorize("hasRole('ROLE_RECRUITEUR') and hasAuthority('CREATE_OFFER')")
    @PostMapping("/createOffer")

    public ResponseEntity<Offre> owaisController(@RequestBody OffreDTO offreDTO, @AuthenticationPrincipal User recruteur) {

        // N-call l-service w nsayb l'offre
        Offre createdOffer = recruiterService.createOffer(offreDTO, recruteur);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }


    // Endpoint pour récupérer les candidatures du recruteur
    @GetMapping("/{recruiterId}/candidacies")
    @PreAuthorize("hasRole('ROLE_RECRUITER') and hasAuthority('VIEW_CANDIDACIES')")
    public ResponseEntity<List<CandidatureDTO>> getCandidaciesForRecruiter(@PathVariable Integer recruiterId) {
        List<CandidatureDTO> candidacies = recruiterService.getCandidaciesForRecruiter(recruiterId);
        return ResponseEntity.ok(candidacies);
    }

    @PutMapping("/candidacies/{candidacyId}/status")
    @PreAuthorize("hasRole('ROLE_RECRUTEUR') and hasAuthority('MODIFY_CANDIDACY_STATUS')")
    public ResponseEntity<?> updateCandidacyStatus(@PathVariable Integer candidacyId, @RequestParam Integer newStatusId) {
        recruiterService.updateCandidacyStatus(candidacyId, newStatusId);
        return ResponseEntity.ok("Status updated successfully");
    }




}
