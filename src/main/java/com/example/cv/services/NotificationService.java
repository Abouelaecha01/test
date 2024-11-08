package com.example.cv.services;


import com.example.cv.entities.Notification;
import com.example.cv.sercurityglobal.entity.User;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    Notification createNotification(User user, String message, String type);
    List<Notification> getUnreadNotifications(User user);
    void markAsRead(Notification notification);
    Optional<Notification> getNotificationById(Integer id);

}
