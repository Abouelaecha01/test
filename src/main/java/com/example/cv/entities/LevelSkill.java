package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ref_Level_Skill")
public class LevelSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long levelSkillID;
    private String levelSkillName;

}