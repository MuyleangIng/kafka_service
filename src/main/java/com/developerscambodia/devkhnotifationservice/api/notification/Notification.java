package com.developerscambodia.devkhnotifationservice.api.notification;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity (name = "notifications")
@Table
public class  Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String title;

    private String description;

    private String thumbnail;

    private LocalDateTime sendAt;

}
