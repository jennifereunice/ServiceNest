package com.servicenest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicenest.model.Notification;


public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);
}

