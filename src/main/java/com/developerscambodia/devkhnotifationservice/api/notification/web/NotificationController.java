package com.developerscambodia.devkhnotifationservice.api.notification.web;

import com.developerscambodia.devkhnotifationservice.api.notification.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    // this method for received Notification Course
    @MessageMapping("/notification-course")
    @SendTo("/topic/course")
    public Notification receiveNotificationCourser(@Payload Notification notification) {
        return notification;
    }


    // this method for received Notification Blog
//    @MessageMapping("/notification-blog")
//    @SendTo("/topic/blog")
//    public Notification receiveNotificationBlog(@Payload Notification notification) {
//        return notification;
//    }

}
