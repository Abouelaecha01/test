package com.example.cv.dto;

import java.util.Date;

import lombok.Data;


@Data
public class CvPersonalInfoDTO {
    private String nom;
    private String prenom;
    private Date dateDeNaissance;
    private String email;
    private String tel1;
    private String fixmobile;
    private String address;
    private String linkedin;
    private String profile;
    private Long templateId;
// Getters and Setters for templateId


}
