package com.example.cv.entities;


import com.example.cv.sercurityglobal.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    private String message;

    private String type; // e.g., "Candidature Update", "New Offer"

    private Boolean isRead = false;
    private Date dateNotification;






}
