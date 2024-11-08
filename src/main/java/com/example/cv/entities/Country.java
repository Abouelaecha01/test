package com.example.cv.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Ref_Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryID;
    private String countryName;
    @OneToMany(mappedBy = "country")
    @JsonManagedReference
    private List<Offre> offers;
}
