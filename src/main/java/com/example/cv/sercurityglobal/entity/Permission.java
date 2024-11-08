package com.example.cv.sercurityglobal.entity;


import jakarta.persistence.*;
import lombok.Data;


import java.util.Set;


@Data
@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permissionId;

    private String nomPermission;
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;


}
