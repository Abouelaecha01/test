package com.example.cv.controllers;


import com.example.cv.entities.Notification;
import com.example.cv.sercurityglobal.entity.User;
import com.example.cv.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications(@AuthenticationPrincipal User user) {
        List<Notification> notifications = notificationService.getUnreadNotifications(user);
        return ResponseEntity.ok(notifications);
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Integer id) {
        Notification notification = notificationService.getNotificationById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notificationService.markAsRead(notification);
        return ResponseEntity.ok().build();
    }
}
