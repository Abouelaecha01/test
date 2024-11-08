package com.example.cv.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Cv_Skill")
public class CvSkill { // f Java class khassha tkoun CamelCase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long cvSkillID; // f Java variable khassha tkoun camelCase
    
    @ManyToOne
    @JoinColumn(name = "cvID")
    @JsonIgnore
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "Skill_ID")
    private Skill skill;

    @ManyToOne
    @JoinColumn(name ="levelSkill_ID")
    private LevelSkill levelSkill;

    private Date createdAt;
    private Date updatedAt;

}
