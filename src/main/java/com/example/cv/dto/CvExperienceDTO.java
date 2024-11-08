package com.example.cv.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CvExperienceDTO {
    private Long companyID;
    private Date dateDebut;
    private Date dateFin;
    private String description;
    private String fonction;
    private Long countryID;


}
