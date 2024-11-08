package com.example.cv.sercurityglobal.dto;


import lombok.Data;

@Data
public class UserDetailsDTO {

    private Integer userId;
    private String nom;
    private String prenom;
    private String email;
    private String role;

}
