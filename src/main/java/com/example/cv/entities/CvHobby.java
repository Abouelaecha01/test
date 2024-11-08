package com.example.cv.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Cv_Hobby")
public class CvHobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvHobbyID;

    @ManyToOne
    @JoinColumn(name = "cvID")
    @JsonIgnore
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "Hobby_ID")
    private Hobby hobby;

    private Date createdAt;
    private Date updatedAt;

}
