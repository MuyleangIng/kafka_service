package com.developerscambodia.devkhnotifationservice.api.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final SimpMessagingTemplate template;
    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "course-new", groupId = "course-id" , containerFactory = "kafkaListenerContainerFactoryCourse")
    public void consumeCourseService(@Payload Notification notification, Acknowledgment acknowledgment) {
        try {

            notification.setUuid(UUID.randomUUID().toString());

            notification.setSendAt(LocalDateTime.now());

            // Save to DataBase
            notificationRepository.save(notification);

            // send to destination to subscribe to destination "/topic/course"
             template.convertAndSend("/topic/course", notification);

            // acknowledgment mechanism in kafka provide between ensuring data durability.
            acknowledgment.acknowledge();

        } catch (Exception e) {

            // Log the exception
            log.error("Error processing Kafka message: {}", e.getMessage(), e);
        }
    }

}

