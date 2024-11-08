package com.example.cv.repositories;

import com.example.cv.entities.Notification;
import com.example.cv.sercurityglobal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUserAndIsReadFalse(User user);
}
