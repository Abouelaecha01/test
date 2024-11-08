package com.example.cv.dto;

import com.example.cv.sercurityglobal.dto.UserDetailsDTO;
import lombok.Data;

import java.util.Date;

@Data
public class CandidatureDTO {

    private Integer candidatureId;
    private Date dateApplication;

    private Integer statutId;
    private String nomStatut;

    private Integer userId;
    private String userName;
    private String userEmail;

    private Integer offerId;
    private String offerTitle;

    // Adding fields and getters/setters if needed
    public void setStatut(CandidatureStatutDTO statut) {
        this.statutId = statut.getStatutId();
        this.nomStatut = statut.getNomStatut();
    }

    public void setUser(UserDetailsDTO user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userEmail = user.getEmail();
    }

    public void setOffre(OffreDTO offre) {
        this.offerId = offre.getOfferID();
        this.offerTitle = offre.getTitle();
    }
}
