package com.developerscambodia.devkhnotifationservice.api.notification;

import com.developerscambodia.devkhnotifationservice.api.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository  extends JpaRepository<Notification , Long> {
}
