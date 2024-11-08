package com.example.cv.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OffreDTO {
    private Integer offerID;
    private String title;
    private String description;
    private Long contractTypeId;
    private String contractTypeName;
    private Double salaire;
    private Long countryID;
    private String countryName;
    private Date datePublication;

}

