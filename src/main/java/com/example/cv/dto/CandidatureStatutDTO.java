package com.example.cv.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Default constructor
@AllArgsConstructor // Constructor with parameters (statutId and nomStatut)
public class CandidatureStatutDTO {

    private Integer statutId;

    private String nomStatut;
}
