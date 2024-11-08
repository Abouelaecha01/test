package com.example.cv.dto;

import lombok.Data;

@Data
public class CompanyDTO {
    private Long companyID;
    private String companyName;
    private String address;
    private String tel;
    private String typeOfCompany;
    private byte[] companyImage;
}
